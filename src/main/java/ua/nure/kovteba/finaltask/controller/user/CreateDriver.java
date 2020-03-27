package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "createDriver",
        urlPatterns = "/createDriver"
)
public class CreateDriver extends HttpServlet {

    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAOImpl();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String phoneNumber = req.getParameter("phoneNumberDriver");
        User user = userDAO.getUserByUserPhoneNumber(phoneNumber);
        if (user == null){
            User newDriver = new User();
            newDriver.setFirstName(req.getParameter("firstNameDriver"));
            newDriver.setLastName(req.getParameter("lastNameDriver"));
            newDriver.setPhoneNumber(phoneNumber);
            newDriver.setPassword(req.getParameter("passwordDriver"));
            newDriver.setRole(Role.DRIVER);
            userDAO.createUser(newDriver);
        } else {
            System.out.println("User with " + phoneNumber + " already exist");
        }




        String token = req.getParameter("token");
        System.out.println("DRIVER : " + token);
        resp.sendRedirect("admin?token=" + token + "&value=DRIVER");
    }
}
