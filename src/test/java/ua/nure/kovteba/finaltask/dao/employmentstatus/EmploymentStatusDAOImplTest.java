package ua.nure.kovteba.finaltask.dao.employmentstatus;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.enumlist.Employment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentStatusDAOImplTest {

    private static EmploymentStatusDAOImpl employmentStatusDAO;

    static {
        employmentStatusDAO = new EmploymentStatusDAOImpl();
    }

    @Test
    void createEmploymentStatus() {
        employmentStatusDAO.createEmploymentStatus(75L, Employment.FREE);
    }

    @Test
    void changeEmploymentStatus() {
        employmentStatusDAO.changeEmploymentStatus(75L, Employment.FREE);
    }

    @Test
    void getAllFreeDrivers() {
        List<Long> list = employmentStatusDAO.getAllFreeDrivers();
        if (list.size() == 0){
            System.out.println("Value : " + list.size());
        } else {
            for (Long l : list){
                System.out.println(l);
            }
        }
    }
}