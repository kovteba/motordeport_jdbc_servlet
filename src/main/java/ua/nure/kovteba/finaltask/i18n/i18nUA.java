package ua.nure.kovteba.finaltask.i18n;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "i18nUA",
        urlPatterns = "/ua"
)
public class i18nUA extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String pageName = req.getParameter("pageName");

        req.getSession().setAttribute("i18n", "MessagesBundle_ua_UA");

        resp.sendRedirect(pageName + "?token=" + token);    }
}