package ua.nure.kovteba.finaltask.dao.user;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.entity.EmploymentStatus;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.util.*;


class UserDAOImplTest {

    private static final UserDAOImpl USER_DAO;

    private static final DataFactory DATA_FACTORY;

    private static final EmploymentStatusDAOImpl EMPLOYMENT_STATUS_DAO;

    static {
        USER_DAO = new UserDAOImpl();
        EMPLOYMENT_STATUS_DAO = new EmploymentStatusDAOImpl();
        DATA_FACTORY = new DataFactory();
    }

    @Test
    void createUser() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            Random random = new Random();
            String firstName = null;
            for (int j = 0; j < random.nextInt(100); j++) {
                firstName = DATA_FACTORY.getFirstName();
            }
            String lastName = null;
            for (int j = 0; j < random.nextInt(100); j++) {
                lastName = DATA_FACTORY.getLastName();
            }
            String number = null;
            for (int j = 0; j < random.nextInt(100); j++) {
                number = String.valueOf(DATA_FACTORY.getNumberBetween(1000000, 9999999));
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber("050" + number);
            user.setRole(Role.DISPATCHER);
            user.setPassword(lastName);
            Long idNewUser = USER_DAO.createUser(user);
            System.out.println(idNewUser);
        }
    }

    @Test
    void getUserByRole() {
        List<User> userList = USER_DAO.getUserByRole(Role.DRIVER);
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    void getUserById() {
        User user = USER_DAO.getUserById(1L);
        System.out.println(user == null);
    }

    @Test
    void getUserByUserPhoneNumber() {
        System.out.println(USER_DAO.getUserByUserPhoneNumber("0509769376").toString());
    }

    @Test
    void getAllUsers() {
        Map<User, String> map = new HashMap<>();
        List<User> userList = USER_DAO.getAllUsers();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        List<EmploymentStatus> employmentStatuses = EMPLOYMENT_STATUS_DAO.getAllValueEmployment();

        for (User user : userList) {
            for (EmploymentStatus employmentStatus : employmentStatuses) {
                if (user.getId().equals(employmentStatus.getIdDriver())) {
                    map.put(user, employmentStatus.getValue());
                }
            }
        }

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    @Test
    void deleteUserById() {
        USER_DAO.deleteUserById(135L);
    }

    @Test
    void getAllUsersByListId() {
        List<User> list = USER_DAO.getAllUsersByListId(EMPLOYMENT_STATUS_DAO.getAllFreeDrivers());
        for (User user : list) {
            System.out.println(user.toString());
        }
    }


}