package ua.nure.kovteba.finaltask.dao.employmentstatus;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.entity.EmploymentStatus;
import ua.nure.kovteba.finaltask.enumlist.Employment;

import java.util.List;

class EmploymentStatusDAOImplTest {

    private static final EmploymentStatusDAOImpl EMPLOYMENT_STATUS_DAO;

    static {
        EMPLOYMENT_STATUS_DAO = new EmploymentStatusDAOImpl();
    }

    @Test
    void createEmploymentStatus() {
        EMPLOYMENT_STATUS_DAO.createEmploymentStatus(151L, Employment.FREE);
    }

    @Test
    void changeEmploymentStatus() {
        EMPLOYMENT_STATUS_DAO.changeEmploymentStatus(151L, Employment.FREE);
    }

    @Test
    void getAllFreeDrivers() {
        List<Long> list = EMPLOYMENT_STATUS_DAO.getAllFreeDrivers();
        if (list.size() == 0){
            System.out.println("Value : " + list.size());
        } else {
            for (Long l : list){
                System.out.println(l);
            }
        }
    }

    @Test
    void deleteEmploymentStatusByDriverId() {
        EMPLOYMENT_STATUS_DAO.deleteEmploymentStatusByDriverId(151L);
    }

    @Test
    void getAllValueEmployment() {
        for (EmploymentStatus employmentStatus : EMPLOYMENT_STATUS_DAO.getAllValueEmployment()){
            System.out.println(employmentStatus.toString());
        }
    }
}