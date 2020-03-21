package ua.nure.kovteba.finaltask.dao.user;

import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.util.List;

public interface UserDAO {

    Long createUser(User user);

    List<User> getUserByRole(Role role);

    User getUserById(Long id);

    User getUserByUserPhoneNumber(String phoneNUmber);

    List<User> getAllUsers();



}
