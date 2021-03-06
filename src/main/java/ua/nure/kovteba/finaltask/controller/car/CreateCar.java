package ua.nure.kovteba.finaltask.controller.car;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;
import ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "createCar",
        urlPatterns = "/createCar"
)
public class CreateCar extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(Admin.class.getName());

    private static CarDAOImpl carDAO;
    private static CarBrandDAOImpl carBrandDAO;
    private static TokenDAO tokenDAO;
    private static UserDAOImpl userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carDAO = new CarDAOImpl();
        carBrandDAO = new CarBrandDAOImpl();
        tokenDAO = new TokenDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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
            if (req.getParameter("carBrandId") != null){
                if (req.getParameter("carClassValue") != null){
                    if (req.getParameter("carTechnicalStatusValue") != null){
                        Car car = new Car();
                        car.setCarBrand(carBrandDAO.getCarBrandById(Long.valueOf(req.getParameter("carBrandId"))));
                        car.setCarClass(CarClass.findCarClass(req.getParameter("carClassValue")));
                        car.setCarTechnicalStatus(CarTechnicalStatus.findCarTechnicatlStatus(req.getParameter("carTechnicalStatusValue")));
                        if (req.getParameter("luggageCompartment") == null) {
                            car.setLuggageCompartment(false);
                        } else {
                            car.setLuggageCompartment(true);
                        }
                        if (req.getParameter("airConditioning") == null) {
                            car.setAirConditioning(false);
                        } else {
                            car.setAirConditioning(true);
                        }
                        if (req.getParameter("navigator") == null){
                            car.setNavigator(false);
                        } else {
                            car.setNavigator(true);
                        }
                        car.setCarNumber(req.getParameter("carNumber"));
                        car.setLoadCapacity(Integer.parseInt(req.getParameter("loadCapacity")));
                        car.setSeats(Integer.parseInt(req.getParameter("seats")));
                        car.setCarStatus(CarStatus.FREE);
                        //add new car
                        carDAO.createCar(car);
                    }
                }
            }
        } else {
            resp.sendRedirect("");
        }

        resp.sendRedirect("admin?value=CAR");

    }
}
