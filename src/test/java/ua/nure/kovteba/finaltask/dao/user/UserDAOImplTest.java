package ua.nure.kovteba.finaltask.dao.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;


class UserDAOImplTest {

    private static UserDAOImpl userDAO;

    static {
        userDAO = new UserDAOImpl();
    }

    @Test
    void createUser() {
    }

    @Test
    void getUserByRole() {

    }

    @Test
    void getUserById() {
        System.out.println(userDAO.getUserById(1L).toString());
    }

    @Test
    void getUserByUserPhoneNumber() {
    }

    @Test
    void getAllUsers() {
    }
}