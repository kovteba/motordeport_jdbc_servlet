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
import java.util.logging.Logger;

@WebServlet(
        name = "index",
        urlPatterns = "/index"
)
public class Index extends HttpServlet {

    //Create logger
    private static Logger LOG = Logger.getLogger(Index.class.getName());

    private static UserDAOImpl userDAO;
    private static TokenDAOImpl tokenDAO;
    private static FlightDAOImpl flightDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
        flightDAO = new FlightDAOImpl();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //flights and requests section
        req.setAttribute("flightsList", flightDAO.getAllFlight());

        System.out.println("FROM INDEX");
        //set token
        req.setAttribute("token", "null");
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/templates/index.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("FROM INDEX POST");

        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        String token = null;
        User user = userDAO.getUserByUserPhoneNumber(phoneNumber);

        if (user != null){
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
            LOG.info("User with --> " + phoneNumber +" dont find.");
        }




    }


}
