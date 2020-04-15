package ua.nure.kovteba.finaltask.controller.car;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAO;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAO;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
import ua.nure.kovteba.finaltask.entity.CarBrand;
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
        name = "changeInfoCar",
        urlPatterns = "/changeInfoCar"
)
public class ChangeCarInformation  extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(ChangeCarInformation.class.getName());

    private static CarDAO carDAO;
    private static TokenDAO tokenDAO;
    private static UserDAO userDAO;
    private static CarBrandDAO carBrandDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carDAO = new CarDAOImpl();
        tokenDAO = new TokenDAOImpl();
        userDAO = new UserDAOImpl();
        carBrandDAO = new CarBrandDAOImpl();
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

        System.out.println("@@@@@@@@@@@@@@@@");
        if (user != null && user.getRole().getRoleValue().equals("ADMIN")){
            //change car information
            System.out.println("!!!!!!!!!!!!!!!!!!!");
            if (req.getParameter("carBrandId") != null){
                System.out.println("%%%%%%%%%%%");
                if (req.getParameter("carClassValue") != null){
                    System.out.println("^^^^^^^^^^^^");
                    if (req.getParameter("carTechnicalStatusValue") != null){
                        System.out.println("&&&&&&&&&&&&&&");
                        Car car = new Car();
                        System.out.println("car id : " + req.getParameter("carIdForChange"));
                        car.setId(Long.valueOf(req.getParameter("carIdForChange")));
                        System.out.println("car brand : " + req.getParameter("carBrandId"));
                        car.setCarBrand(carBrandDAO.getCarBrandByBrandValue(req.getParameter("carBrandId")));
                        System.out.println("car calss : " + req.getParameter("carClassValue"));
                        car.setCarClass(CarClass.findCarClass(req.getParameter("carClassValue")));
                        System.out.println("tech : " + req.getParameter("carTechnicalStatusValue"));
                        car.setCarTechnicalStatus(CarTechnicalStatus.findCarTechnicatlStatus(req.getParameter("carTechnicalStatusValue")));
                        System.out.println("luggage : " + req.getParameter("luggageCompartment"));
                        if (req.getParameter("luggageCompartment") == null) {
                            car.setLuggageCompartment(false);
                        } else {
                            car.setLuggageCompartment(true);
                        }
                        System.out.println("air : " + req.getParameter("airConditioning"));
                        if (req.getParameter("airConditioning") == null) {
                            car.setAirConditioning(false);
                        } else {
                            car.setAirConditioning(true);
                        }
                        System.out.println("navigator : " + req.getParameter("navigator"));
                        if (req.getParameter("navigator") == null){
                            car.setNavigator(false);
                        } else {
                            car.setNavigator(true);
                        }
                        System.out.println("number : " + req.getParameter("carNumber"));
                        car.setCarNumber(req.getParameter("carNumber"));
                        System.out.println("load : " + req.getParameter("loadCapacity"));
                        car.setLoadCapacity(Integer.parseInt(req.getParameter("loadCapacity")));
                        System.out.println("seats : " + req.getParameter("seats"));
                        car.setSeats(Integer.parseInt(req.getParameter("seats")));
                        car.setCarStatus(CarStatus.FREE);
                        //add new car
                        carDAO.changeCarInformation(car);
                    }
                }
            }
        } else {
            System.out.println("###############");
            resp.sendRedirect("");
        }

        resp.sendRedirect("admin?value=CAR");

    }

}
