package ua.nure.kovteba.finaltask.dao.request;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

import java.util.List;

class RequestDAOImplTest {

    private static final UserDAOImpl USER_DAO;
    private static final RequestDAOImpl REQUEST_DAO;

    static {
        USER_DAO = new UserDAOImpl();
        REQUEST_DAO = new RequestDAOImpl();
    }

    @Test
    void createRequest() {
        Request request = new Request();
        request.setDriver(USER_DAO.getUserById(1L));
        request.setCarClass(CarClass.COMFORT);
        request.setLoadCapacity(1000);
        request.setSeats(20);
        request.setLuggageCompartment(true);
        request.setAirConditioning(true);
        request.setNavigator(true);
        request.setRequestStatus(RequestStatus.OPEN);
        Long newRequestId = REQUEST_DAO.createRequest(request);
        Request findNewRequest = REQUEST_DAO.getRequestById(newRequestId);


    }

    @Test
    void getAllRequestByStatus() {
        List<Request> list = REQUEST_DAO.getAllRequestByStatus(RequestStatus.OPEN);
        for (Request request : list){
            System.out.println(request.toString());
        }
    }

    @Test
    void getRequestById() {
        System.out.println(REQUEST_DAO.getRequestById(36L));
    }

    @Test
    void changeStatusRequestById() {
        REQUEST_DAO.changeStatusRequestById(36L, RequestStatus.CLOSED);
    }

    @Test
    void getAllRequest() {
        List<Request> list = REQUEST_DAO.getAllRequest();
        for (Request request : list){
            System.out.println(request.toString());
        }
    }

    @Test
    void getAllRequestByDriver() {
        List<Request> list = REQUEST_DAO.getAllRequestByDriver(USER_DAO.getUserById(133L));
        for (Request request : list){
            System.out.println(request.toString());
        }
    }

    @Test
    void getAllRequestByDriverAndRequestStatus() {
        List<Request> list = REQUEST_DAO.getAllRequestByDriverAndRequestStatus(USER_DAO.getUserById(133L), RequestStatus.CLOSED);
        for (Request request : list){
            System.out.println(request.toString());
        }
    }
}