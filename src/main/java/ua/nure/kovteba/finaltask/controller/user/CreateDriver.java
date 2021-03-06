package ua.nure.kovteba.finaltask.controller.user;

import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAO;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Employment;
import ua.nure.kovteba.finaltask.enumlist.Role;
import ua.nure.kovteba.finaltask.exception.SomethingWrongException;
import ua.nure.kovteba.finaltask.util.Encryption;

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
import java.util.logging.Logger;

@WebServlet(
    name = "createDriver",
    urlPatterns = "/createDriver"
)
public class CreateDriver extends HttpServlet {

   //Create logger
   private static Logger log = Logger.getLogger(CreateDriver.class.getName());

   private static UserDAO userDAO;
   private static EmploymentStatusDAO employmentStatusDAO;
   private static TokenDAO tokenDAO;

   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      userDAO = new UserDAOImpl();
      employmentStatusDAO = new EmploymentStatusDAOImpl();
      tokenDAO = new TokenDAOImpl();
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      String userToken = "0";

      if (req.getSession().getAttribute("userToken") != null) {
         userToken = String.valueOf(req.getSession().getAttribute("userToken"));
      }

      log.info("user token session--> " + userToken + ", class: " + this.getClass());

      User user = null;
      if (!userToken.equals("0")) {
         user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());
      }

      if (user != null && user.getRole().getRoleValue().equals("ADMIN")) {
         //find user by phone number
         String phoneNumber = req.getParameter("phoneNumberDriver");
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
            //create new user with role "Driver"
            User newDriver = new User();
            newDriver.setFirstName(req.getParameter("firstNameDriver"));
            newDriver.setLastName(req.getParameter("lastNameDriver"));
            newDriver.setPhoneNumber(phoneNumber);
            newDriver.setPassword(Encryption.SHA256(req.getParameter("passwordDriver")));
            newDriver.setRole(Role.DRIVER);
            newDriver.setEmail(req.getParameter("emailDriver"));
            Long newDriverId = userDAO.createUser(newDriver);

            //redirect to admin
            resp.sendRedirect("admin?value=DRIVER");
         }

      } else {
         resp.sendRedirect("");
      }


   }
}
