package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "driver",
        urlPatterns = "/driver"
)
public class Driver extends HttpServlet {

    private static UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getParameter("token");

        System.out.println("TOKEN FROM DRIVER SERVLET : " + token);
        User newDriver = new User();
        newDriver.setFirstName(req.getParameter("firstName"));
        newDriver.setLastName(req.getParameter("lastName"));
        newDriver.setPhoneNumber(req.getParameter("phoneNumber"));
        newDriver.setPassword(req.getParameter("password"));
        newDriver.setRole(Role.DRIVER);
        userDAO.createUser(newDriver);



//        req.getRequestDispatcher("admin").forward(req, resp);
//        resp.setHeader("token", "KOVTEBA");
//        resp.addHeader("token", "KOVTEBA");

        req.setAttribute("token", token);
        resp.sendRedirect("admin");
    }
}
