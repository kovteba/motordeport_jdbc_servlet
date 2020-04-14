package ua.nure.kovteba.finaltask.i18n;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "i18nUs",
        urlPatterns = "/us"
)
public class i18nUS extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");

        req.getSession().setAttribute("i18n", "MessagesBundle_en_US");

        resp.sendRedirect("admin?token=" + token);
    }
}
