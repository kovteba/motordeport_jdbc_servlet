package ua.nure.kovteba.finaltask.controller.user;

import com.google.common.net.MediaType;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.IOUtils;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAO;
import ua.nure.kovteba.finaltask.dao.flight.FlightDAOImpl;
import ua.nure.kovteba.finaltask.dao.token.TokenDAO;
import ua.nure.kovteba.finaltask.dao.token.TokenDAOImpl;
import ua.nure.kovteba.finaltask.dao.user.UserDAO;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Token;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.util.GeneratePDF;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

@WebServlet(
        name = "allFlightsByUser",
        urlPatterns = "/genpdf"
)
public class FlightsByUser extends HttpServlet {

    //Create logger
    private static Logger log = Logger.getLogger(FlightsByUser.class.getName());
    private static UserDAO userDAO;
    private static TokenDAO tokenDAO;
    private static GeneratePDF generatePDF;
    private static String directory;
    private static FlightDAO flightDAO;
    private static final Properties PROPERTIES = new Properties();

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAOImpl();
        tokenDAO = new TokenDAOImpl();
        generatePDF = new GeneratePDF();
        flightDAO = new FlightDAOImpl();
        FileInputStream fileWithPropertiesForDB = null;
        try {
            fileWithPropertiesForDB = new FileInputStream("src/main/resources/application.properties");
            PROPERTIES.load(fileWithPropertiesForDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        directory = PROPERTIES.getProperty("files.storage");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get user token from session
        String userToken = String.valueOf(req.getSession().getAttribute("userToken"));

        log.info("user token session--> " + userToken + ", class: " + this.getClass());
        String fileName = null;
        Token token = tokenDAO.getTokenByToken(userToken);
        User user = userDAO.getUserById(token.getUser());
        if (token != null && user.getRole().getRoleValue().equals("DRIVER")) {
            try {
                fileName = generatePDF.generateFlightsByUserPDF(user, flightDAO.getAllFlightByDriver(user));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            File file = new File(directory + fileName);
            InputStream targetStream = new DataInputStream(new FileInputStream(file));
            resp.setContentType(String.valueOf(MediaType.PDF));
            IOUtils.copy(targetStream, resp.getOutputStream());
        }
    }


}
