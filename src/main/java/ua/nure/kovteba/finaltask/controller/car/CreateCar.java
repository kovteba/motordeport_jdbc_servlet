package ua.nure.kovteba.finaltask.controller.car;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.entity.Car;
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
import java.nio.file.LinkOption;
import java.util.logging.Logger;

@WebServlet(
        name = "createCar",
        urlPatterns = "/createCar"
)
public class CreateCar extends HttpServlet {

    //Create logger
    private static Logger LOG = Logger.getLogger(Admin.class.getName());

    private static CarDAOImpl carDAO;
    private static CarBrandDAOImpl carBrandDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        carDAO = new CarDAOImpl();
        carBrandDAO = new CarBrandDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.info("Create Car doPost .....");
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
        LOG.info("Add new car --> " + car.toString());
        carDAO.createCar(car);

        String token = req.getParameter("token");
        System.out.println("DRIVER : " + token);
        resp.sendRedirect("admin?token=" + token + "&value=CAR");
    }
}
