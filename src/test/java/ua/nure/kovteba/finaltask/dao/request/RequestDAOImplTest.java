package ua.nure.kovteba.finaltask.dao.request;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.enumlist.CarClass;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestDAOImplTest {

    UserDAOImpl userDAO = new UserDAOImpl();
    RequestDAOImpl requestDAO = new RequestDAOImpl();

    @Test
    void createRequest() {
        Request request = new Request();
        request.setDriver(userDAO.getUserById(1L));
        request.setCarClass(CarClass.COMFORT);
        request.setLoadCapacity(1000);
        request.setSeats(20);
        request.setLuggageCompartment(true);
        request.setAirConditioning(true);
        request.setNavigator(true);
        request.setRequestStatus(RequestStatus.OPEN);
        Long newRequestId = requestDAO.createRequest(request);
        Request findNewRequest = requestDAO.getRequestById(newRequestId);
        request.setId(newRequestId);
        assertTrue(request.getCarClass().equals(findNewRequest.getCarClass()));
        assertTrue(request.getLoadCapacity() == (findNewRequest.getLoadCapacity()));
        assertTrue(request.getSeats() == (findNewRequest.getSeats()));
        assertTrue(request.getLuggageCompartment().equals(findNewRequest.getLuggageCompartment()));
        assertTrue(request.getAirConditioning().equals(findNewRequest.getAirConditioning()));
        assertTrue(request.getNavigator().equals(findNewRequest.getNavigator()));
        assertTrue(request.getRequestStatus().equals(findNewRequest.getRequestStatus()));

    }

    @Test
    void getAllRequestByStatus() {
        List<Request> list = requestDAO.getAllRequestByStatus(RequestStatus.OPEN);
        for (Request request : list){
            System.out.println(request.toString());
        }
    }

    @Test
    void getRequestById() {
    }

    @Test
    void changeStatusRequestById() {
        requestDAO.changeStatusRequestById(10L, RequestStatus.CLOSED);
    }

    @Test
    void getAllRequest() {
        List<Request> list = requestDAO.getAllRequest();
        for (Request request : list){
            System.out.println(request.toString());
        }
    }

    @Test
    void getAllRequestByDriver() {
        List<Request> list = requestDAO.getAllRequestByDriver(userDAO.getUserById(133L));
        for (Request request : list){
            System.out.println(request.toString());
        }
    }

    @Test
    void getAllRequestByDriverAndRequestStatus() {
        List<Request> list = requestDAO.getAllRequestByDriverAndRequestStatus(userDAO.getUserById(133L), RequestStatus.CLOSED);
        for (Request request : list){
            System.out.println(request.toString());
        }
    }
}