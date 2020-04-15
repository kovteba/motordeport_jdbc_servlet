package ua.nure.kovteba.finaltask.controller.request;

import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;
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
        name = "createRequest",
        urlPatterns = "/createRequest"
)
public class CreateRequest extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(CreateRequest.class.getName());

    private static RequestDAOImpl requestDAO;
    private static UserDAOImpl userDAO;
    private static TokenDAO tokenDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        requestDAO = new RequestDAOImpl();
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
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

        if (user != null && user.getRole().getRoleValue().equals("ADMIN")) {
            //create new request
            Request request = new Request();

            if (req.getParameter("freeDriversId") != null) {
                Long idDriver = Long.valueOf(req.getParameter("freeDriversId"));
                request.setDriver(userDAO.getUserById(idDriver));
                if (req.getParameter("carClassForRequest") != null) {
                    String carClassForRequest = req.getParameter("carClassForRequest");
                    request.setCarClass(CarClass.findCarClass(carClassForRequest));
                    request.setLoadCapacity(Integer.parseInt(req.getParameter("loadCapacityForRequest")));
                    request.setSeats(Integer.parseInt(req.getParameter("seatsForRequest")));
                    if (req.getParameter("luggageCompartmentForRequest") == null) {
                        request.setLuggageCompartment(false);
                    } else {
                        request.setLuggageCompartment(true);
                    }
                    if (req.getParameter("airConditioningForRequest") == null) {
                        request.setAirConditioning(false);
                    } else {
                        request.setAirConditioning(true);
                    }
                    if (req.getParameter("navigatorForRequest") == null) {
                        request.setNavigator(false);
                    } else {
                        request.setNavigator(true);
                    }
                    request.setRequestStatus(RequestStatus.OPEN);
                    requestDAO.createRequest(request);
                }
            }
        } else {
            resp.sendRedirect("");
        }

        //redirect to admin
        resp.sendRedirect("admin");
    }

}
