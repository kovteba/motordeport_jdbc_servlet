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
import java.io.IOException;
import java.util.Optional;

@WebServlet(
        name = "createUser",
        urlPatterns = "/createUser"
)
public class CreateUser extends HttpServlet {

    private static UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNUmber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");

        super.doPost(req, resp);
    }

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("studentRecord", userDAO.getUserById(1L));

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user.jsp");
        dispatcher.forward(request, response);
    }


}
