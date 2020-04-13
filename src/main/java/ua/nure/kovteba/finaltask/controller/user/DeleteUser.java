package ua.nure.kovteba.finaltask.controller.user;

import jdk.vm.ci.meta.Local;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(
        name = "deleteUser",
        urlPatterns = "/deleteUser"
)
public class DeleteUser extends HttpServlet {

    private static UserDAOImpl userDAO;
    private static EmploymentStatusDAOImpl employmentStatusDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
        employmentStatusDAO = new EmploymentStatusDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("idDriver"));
        String redirectString = userDAO.getUserById(id).getRole().getRoleValue();
        userDAO.deleteUserById(id);
        employmentStatusDAO.deleteEmploymentStatusByDriverId(id);

        String token = req.getParameter("token");
        System.out.println("DELETE DRIVER : " + token);
        resp.sendRedirect("admin?token=" + token + "&value=" + redirectString);
    }


}
