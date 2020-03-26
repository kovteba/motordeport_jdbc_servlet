package ua.nure.kovteba.finaltask.controller.carbrand;

import ua.nure.kovteba.finaltask.dao.carbrand.CarBrandDAOImpl;

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
    private static Logger LOG = Logger.getLogger(DeleteCarBrand.class.getName());

    private static CarBrandDAOImpl carBrandDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        carBrandDAO = new CarBrandDAOImpl();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("carBrandId") != null){
            carBrandDAO.deleteCarBrand(Long.valueOf(req.getParameter("carBrandId")));
        }

        String token = req.getParameter("token");
        LOG.info("Token --> \"" + token + "\" in " + this.getClass() + " servlet");
        resp.sendRedirect("admin?token=" + token + "&value=CAR");
    }
}
