package ua.nure.kovteba.finaltask.controller.carbrand;

import ua.nure.kovteba.finaltask.controller.user.Admin;
import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;
import ua.nure.kovteba.finaltask.entity.CarBrand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "createCarBrand",
        urlPatterns = "/createCarBrand"
)
public class CreateCarBrand extends HttpServlet {

    //Create logger
    private static Logger LOG = Logger.getLogger(CreateCarBrand.class.getName());

    private static CarBrandDAOImpl carBrandDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        carBrandDAO = new CarBrandDAOImpl();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.info("doPost in createCarBrand servlet");
        CarBrand carBrand = new CarBrand();
        if (req.getParameter("carBrandName") != null){
            carBrand.setBrandName(req.getParameter("carBrandName"));
            //add new car brand in DB
            carBrandDAO.createCarBrand(carBrand);
        }
        LOG.info("Add new car brand with value == " + carBrand.getBrandName());

        String token = req.getParameter("token");
        LOG.info("Token --> \"" + token + "\" in " + this.getClass() + " servlet");
        resp.sendRedirect("admin?token=" + token + "&value=CAR");
    }
}
