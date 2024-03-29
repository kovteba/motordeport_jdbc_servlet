package ua.nure.kovteba.finaltask.dao.request;

import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

import java.util.List;

public interface RequestDAO {

    Long createRequest(Request request);

    List<Request> getAllRequestByStatus(RequestStatus requestStatus);

    Request getRequestById(Long id);

    void changeStatusRequestById(Long id, RequestStatus requestStatus);

    List<Request> getAllRequest();

    List<Request> getAllRequestByDriver(User user);

    List<Request> getAllRequestByDriverAndRequestStatus(User user, RequestStatus requestStatus);

    void deleteRequestById(Long id);

}
