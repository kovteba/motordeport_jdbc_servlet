package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;
import ua.nure.kovteba.finaltask.exception.SomethingWrongException;
import ua.nure.kovteba.finaltask.i18n.i18nRU;
import ua.nure.kovteba.finaltask.util.Encryption;

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
        name = "createDispatcher",
        urlPatterns = "/createDispatcher"
)
public class CreateDispatcher extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(CreateDispatcher.class.getName());

    private static UserDAOImpl userDAO;
    private static TokenDAO tokenDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null){
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        User user = null;
        if (!userToken.equals("0")){
            user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());
        }
//
        String phoneNumber = req.getParameter("phoneNumberDispatcher");
        User findUser = userDAO.getUserByUserPhoneNumber(phoneNumber);

        if (findUser != null) {
            try {
                throw new SomethingWrongException("User with " + phoneNumber + " already exist!!");
            } catch (SomethingWrongException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "User with " + phoneNumber + " already exist!!");
                RequestDispatcher dispatcher = req.getRequestDispatcher(
                    "/WEB-INF/templates/ErrorPage.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            if (user != null && user.getRole().getRoleValue().equals("ADMIN")){
                //create new user with role "Dispatcher"
                User newDispatcher = new User();
                newDispatcher.setFirstName(req.getParameter("firstNameDispatcher"));
                newDispatcher.setLastName(req.getParameter("lastNameDispatcher"));
                newDispatcher.setPhoneNumber(req.getParameter("phoneNumberDispatcher"));
                newDispatcher.setPassword(Encryption.SHA256(req.getParameter("passwordDispatcher")));
                newDispatcher.setRole(Role.DISPATCHER);
                newDispatcher.setEmail(req.getParameter("emailDispatcher"));
                userDAO.createUser(newDispatcher);
            } else {
                resp.sendRedirect("");
            }

            //redirect to admin
            resp.sendRedirect("admin?value=DISPATCHER");
        }



    }

}
