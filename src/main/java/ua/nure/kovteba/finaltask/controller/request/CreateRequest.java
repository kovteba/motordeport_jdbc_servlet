package ua.nure.kovteba.finaltask.controller.request;

import ua.nure.kovteba.finaltask.controller.carbrand.CreateCarBrand;
import ua.nure.kovteba.finaltask.dao.request.RequestDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

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
    private static Logger LOG = Logger.getLogger(CreateRequest.class.getName());

    private static RequestDAOImpl requestDAO;
    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        requestDAO = new RequestDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.info("Servlet sreateRequest ....");

        Request request = new Request();
        Long idDriver = Long.valueOf(req.getParameter("freeDriversId"));
        if (idDriver != null){
            request.setDriver(userDAO.getUserById(idDriver));
        }
        String carClassForRequest = req.getParameter("carClassForRequest");
        if (carClassForRequest != null){
            request.setCarClass(CarClass.findCarClass(carClassForRequest));
        }
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
        if (req.getParameter("navigatorForRequest") == null){
            request.setNavigator(false);
        } else {
            request.setNavigator(true);
        }
        request.setRequestStatus(RequestStatus.OPEN);
        requestDAO.createRequest(request);



        //get token from page
        String token = req.getParameter("token");
        //redirect to admin
        System.out.println("CREATE FLIGHT : " + token);
        resp.sendRedirect("admin?token=" + token);
    }
}
