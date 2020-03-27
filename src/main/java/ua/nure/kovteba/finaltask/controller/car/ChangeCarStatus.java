package ua.nure.kovteba.finaltask.controller.car;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.car.CarDAOImpl;
import ua.nure.kovteba.finaltask.enumlist.CarStatus;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "changeStatus",
        urlPatterns = "/changeStatus"
)
public class ChangeCarStatus extends HttpServlet {

    //Create logger
    private static Logger LOG = Logger.getLogger(Admin.class.getName());

    private static CarDAOImpl carDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        carDAO = new CarDAOImpl();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idCar = Long.valueOf(req.getParameter("carId"));
        String carStatusValue = req.getParameter("carStatusValue");
        System.out.println(idCar + carStatusValue + "!!!!!!!!!!!!!!!!!!!!!!!");
        if (idCar != null && carStatusValue != null){
            carDAO.changeCarStatus(idCar, CarStatus.findCarStatus(carStatusValue));
        }

        String token = req.getParameter("token");
        LOG.info("Token --> \"" + token + "\" in " + this.getClass() + " servlet");
        resp.sendRedirect("admin?token=" + token + "&value=CAR");
    }
}
