package ua.nure.kovteba.finaltask.dao.user;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.dao.employmentstatus.EmploymentStatusDAOImpl;
import ua.nure.kovteba.finaltask.entity.EmploymentStatus;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.util.*;


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
        for (int i = 0; i < 5; i++) {
            User user = new User();
            Random random = new Random();
            String firstName = null;
            for (int j = 0; j < random.nextInt(100); j++) {
                firstName = dataFactory.getFirstName();
            }
            String lastName = null;
            for (int j = 0; j < random.nextInt(100); j++) {
                lastName = dataFactory.getLastName();
            }
            String number = null;
            for (int j = 0; j < random.nextInt(100); j++) {
                number = String.valueOf(dataFactory.getNumberBetween(1000000, 9999999));
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber("050" + number);
            user.setRole(Role.DISPATCHER);
            user.setPassword(lastName);
            Long idNewUser = userDAO.createUser(user);
            System.out.println(idNewUser);
        }
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
        Map<User,String> map = new HashMap<>();
        List<User> userList = userDAO.getAllUsers();
        for (User user : userList){
            System.out.println(user.toString());
        }
        List<EmploymentStatus> employmentStatuses = employmentStatusDAO.getAllValueEmployment();

        for (User user : userList){
            for (EmploymentStatus employmentStatus : employmentStatuses){
                if (user.getId().equals(employmentStatus.getIdDriver())){
                    map.put(user, employmentStatus.getValue());
                }
            }
        }

        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
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