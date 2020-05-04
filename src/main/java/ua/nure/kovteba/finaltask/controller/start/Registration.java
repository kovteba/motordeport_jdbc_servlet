package ua.nure.kovteba.finaltask.controller.start;

import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;
import ua.nure.kovteba.finaltask.util.Encryption;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

@WebServlet(
        name = "registration",
        urlPatterns = "/registration"
)
public class Registration extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(Registration.class.getName());

    private static UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        log.info("Regestration servlet " + this.getClass());

        User user = null;
        Properties properties = new Properties();
        FileInputStream secretCodeProperties = new FileInputStream("src/main/resources/application.properties");
        properties.load(secretCodeProperties);
        String secretCode = properties.getProperty("secret.code");
        if (secretCode.equals(req.getParameter("secretCode"))){
            //find user by phone number
            String phoneNumber = req.getParameter("phoneNumberAdmin");
            User findUser = userDAO.getUserByUserPhoneNumber(phoneNumber);
            if (findUser == null) {
                //create new user with role "ADMIN"
                User newDriver = new User();
                newDriver.setFirstName(req.getParameter("firstNameAdmin"));
                newDriver.setLastName(req.getParameter("lastNameAdmin"));
                newDriver.setPhoneNumber(phoneNumber);
                newDriver.setPassword(Encryption.SHA256(req.getParameter("passwordAdmin")));
                newDriver.setRole(Role.ADMIN);
                newDriver.setEmail(req.getParameter("emailAdmin"));
                Long newDriverId = userDAO.createUser(newDriver);
            } else {
                log.warning("User with " + phoneNumber + " already exist!! " + this.getClass());
            }
        }
        resp.sendRedirect("");
    }
}
