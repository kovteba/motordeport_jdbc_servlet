package ua.nure.kovteba.finaltask.controller.user;

import jdk.vm.ci.meta.Local;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(
        name = "deleteDriver",
        urlPatterns = "/deleteDriver"
)
public class DeleteDriver extends HttpServlet {

    private static UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userDAO.deleteUserById(Long.valueOf(req.getParameter("idDriver")));

        String token = req.getParameter("token");

        System.out.println("DELETE DRIVER : " + token);

        resp.sendRedirect("admin?token=" + token);
    }
}
