package ua.nure.kovteba.finaltask.controller.start;

import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@WebServlet(
        name = "index",
        urlPatterns = "/index"
)
public class Index extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(Index.class.getName());

    private static UserDAOImpl userDAO;
    private static TokenDAOImpl tokenDAO;
    private static FlightDAOImpl flightDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
        flightDAO = new FlightDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        log.info("doGet " + this.getClass() + "...");

        //install default i18n with value US
        if (req.getSession().getAttribute("i18n") == null){
            req.getSession().setAttribute("i18n", "MessagesBundle_en_US");
        }

        //set flights list
        req.setAttribute("flightsList", flightDAO.getAllFlight());

        //set token
        req.setAttribute("token", "null");

        //open index page
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/templates/index.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doPost " + this.getClass() + "...");

        //get value "phoneNumber", "password" from logIn from
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        //find user by phone
        User user = userDAO.getUserByUserPhoneNumber(phoneNumber);
        String token = null;
        if (user != null){
            log.info("User with phoneNumber --> " + phoneNumber + "found successfully!");
            if (user.getPassword().equals(password)) {
                token = tokenDAO.createToken(user.getId());
                String role = user.getRole().getRoleValue();
                if (role.equals("ADMIN")) {
                    resp.sendRedirect("admin?token=" + token);
                } else if (role.equals("DISPATCHER")){
                    resp.sendRedirect("dispatcher?token=" + token);
                } else if (role.equals("DRIVER")){
                    resp.sendRedirect("driver?token=" + token);
                }
            }
        } else {
            resp.setHeader("user", "user with phone number dont find");
            log.warning("User with --> " + phoneNumber +" dont found!!");
//
//            String i18n = String.valueOf(req.getSession().getAttribute("i18n"));
//            ResourceBundle messages = ResourceBundle.getBundle(i18n);
//            req.getSession().setAttribute("user", messages.getString("wrongNumberOrPassword"));

            resp.sendRedirect("");
        }

    }
}
