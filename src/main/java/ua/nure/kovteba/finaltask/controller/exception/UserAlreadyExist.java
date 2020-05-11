package ua.nure.kovteba.finaltask.controller.exception;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name = "userAlreadyExist",
    urlPatterns = "/userExist"
)
public class UserAlreadyExist extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

      String errorMessage = (String) req.getAttribute("errorMessage");

      resp.setContentType("text/html; charset=utf-8");
      try (PrintWriter writer = resp.getWriter()) {
         writer.write("<html><head><title>Error description</title></head><body>");
         writer.write("<h2>" + errorMessage + "</h2>");
         writer.write("<ul>");
         writer.write("</ul>");
         writer.write("</html></body>");
      }
   }

}
