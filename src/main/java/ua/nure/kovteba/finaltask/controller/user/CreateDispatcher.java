package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "createDispatcher",
        urlPatterns = "/createDispatcher"
)
public class CreateDispatcher extends HttpServlet {

    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //create new user with role "Dispatcher"
        User newDriver = new User();
        newDriver.setFirstName(req.getParameter("firstNameDispatcher"));
        newDriver.setLastName(req.getParameter("lastNameDispatcher"));
        newDriver.setPhoneNumber(req.getParameter("phoneNumberDispatcher"));
        newDriver.setPassword(req.getParameter("passwordDispatcher"));
        newDriver.setRole(Role.DISPATCHER);
        userDAO.createUser(newDriver);
        //get token from page
        String token = req.getParameter("token");
        //redirect to admin
        System.out.println("CREATE FLIGHT : " + token);
        resp.sendRedirect("admin?token=" + token + "&value=DISPATCHER");
    }

}
