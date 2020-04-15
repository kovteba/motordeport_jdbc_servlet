package ua.nure.kovteba.finaltask.controller.carbrand;

import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
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
        name = "deleteCarBrand",
        urlPatterns = "/deleteCarBrand"
)
public class DeleteCarBrand extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(DeleteCarBrand.class.getName());

    private static CarBrandDAOImpl carBrandDAO;
    private static TokenDAO tokenDAO;
    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carBrandDAO = new CarBrandDAOImpl();
        tokenDAO = new TokenDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userToken = "0";

        if (req.getSession().getAttribute("userToken") != null){
            userToken = String.valueOf(req.getSession().getAttribute("userToken"));
        }

        log.info("user token session--> " + userToken + ", class: " + this.getClass());

        User user = null;
        if (!userToken.equals("0")){
            user = userDAO.getUserById(tokenDAO.getTokenByToken(userToken).getUser());
        }

        if (user != null && user.getRole().getRoleValue().equals("ADMIN")){
            if (req.getParameter("carBrandId") != null){
                carBrandDAO.deleteCarBrand(Long.valueOf(req.getParameter("carBrandId")));
            }
        }

        resp.sendRedirect("admin?value=CAR");
    }
}
