package ua.nure.kovteba.finaltask.dao.user;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.util.List;
import java.util.Random;


class UserDAOImplTest {

    private static UserDAOImpl userDAO;

    private static DataFactory dataFactory = new DataFactory();

    private static EmploymentStatusDAOImpl employmentStatusDAO;

    static {
        userDAO = new UserDAOImpl();
        employmentStatusDAO = new EmploymentStatusDAOImpl();
    }

    @Test
    void createUser() {
        User user = new User();
        String lastName = dataFactory.getLastName();
        user.setFirstName(dataFactory.getFirstName());
        user.setLastName(lastName);
        user.setPhoneNumber("050986532541");
        user.setRole(Role.DRIVER);
        user.setPassword(lastName);
        Long idNewUser = userDAO.createUser(user);
    }

    @Test
    void getUserByRole() {
        List<User> userList = userDAO.getUserByRole(Role.DRIVER);
        for (User user : userList){
            System.out.println(user.toString());
        }
    }

    @Test
    void getUserById() {
        User user = userDAO.getUserById(2L);
        System.out.println(user == null);
    }

    @Test
    void getUserByUserPhoneNumber() {
        System.out.println(userDAO.getUserByUserPhoneNumber("0509769376").toString());
    }

    @Test
    void getAllUsers() {
        List<User> userList = userDAO.getAllUsers();
        for (User user : userList){
            System.out.println(user.toString());
        }
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getAllUsersByListId() {
        List<User> list = userDAO.getAllUsersByListId(employmentStatusDAO.getAllFreeDrivers());
        for (User user : list){
            System.out.println(user.toString());
        }
    }
}