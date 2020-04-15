package ua.nure.kovteba.finaltask.controller.car;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;
import ua.nure.kovteba.finaltask.enumlist.Role;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "changeTechnicalStatus",
        urlPatterns = "/changeTechnicalStatus"
)
public class ChangeTechnicalStatus extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(Admin.class.getName());

    private static CarDAOImpl carDAO;
    private static TokenDAO tokenDAO;
    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carDAO = new CarDAOImpl();
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
            //create new user with role "Dispatcher"
            Long idCar = Long.valueOf(req.getParameter("carId"));
            if (req.getParameter("technicalValue") != null){
                String technicalStatus = req.getParameter("technicalValue");
                carDAO.changeCarTechnicalStatus(idCar, CarTechnicalStatus.findCarTechnicatlStatus(technicalStatus));
            }
        } else {
            resp.sendRedirect("");
        }

        resp.sendRedirect("admin?value=CAR");

    }
}
