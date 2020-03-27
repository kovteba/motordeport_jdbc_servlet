package ua.nure.kovteba.finaltask.dao.employmentstatus;

import ua.nure.kovteba.finaltask.enumlist.Employment;

import java.util.List;

public interface EmploymentStatusDAO {

    Long createEmploymentStatus(Long idDriver, Employment employmentValue);

    void changeEmploymentStatus(Long idDriver, Employment employmentValue);

    List<Long> getAllFreeDrivers();

    void deleteEmploymentStatusByDriverId(Long id);
}
