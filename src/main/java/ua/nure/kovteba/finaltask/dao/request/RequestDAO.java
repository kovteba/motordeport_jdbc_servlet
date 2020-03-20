package ua.nure.kovteba.finaltask.dao.request;

import ua.nure.kovteba.finaltask.entity.Request;
import ua.nure.kovteba.finaltask.enumlist.RequestStatus;

import java.util.List;

public interface RequestDAO {

    void createRequest(Request request);

    List<Request> getAllRequestByStatus(RequestStatus requestStatus);
    

}
