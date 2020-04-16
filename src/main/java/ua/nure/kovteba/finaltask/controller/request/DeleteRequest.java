package ua.nure.kovteba.finaltask.controller.request;

import ua.nure.kovteba.finaltask.controller.car.ChangeCarInformation;
import ua.nure.kovteba.finaltask.dao.car.CarDAO;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.request.RequestDAO;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "deleteRequest",
        urlPatterns = "/deleteRequest"
)
public class DeleteRequest extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(DeleteRequest.class.getName());

    private static RequestDAO requestDAO;
    private static TokenDAO tokenDAO;
    private static UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        requestDAO = new RequestDAOImpl();
        tokenDAO = new TokenDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null) {
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        User user = null;
        if (!userToken.equals("0")) {
            user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());
        }

        String redirectString = null;
        if (user != null && user.getRole().getRoleValue().equals("ADMIN")) {
            //create new user with role "Dispatcher"
            Long id = Long.valueOf(req.getParameter("idRequestForDelete"));
            requestDAO.deleteRequestById(id);
        } else {
            resp.sendRedirect("");
        }

        //redirect to admin
        resp.sendRedirect("admin");

    }


}
