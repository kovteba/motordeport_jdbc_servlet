package test.main;

import ua.nure.kovteba.finaltask.dao.user.UserDAOImpl;
import ua.nure.kovteba.finaltask.entity.User;
import ua.nure.kovteba.finaltask.enumlist.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainTest {
    public static void main(String[] args) {

        Optional<Role> role = Role.findRole("USER");
        System.out.println(role.get());

        UserDAOImpl userDAO = new UserDAOImpl();

//        User user = new User();
//        user.setFirstName("Petia");
//        user.setLastName("Petrov");
//        user.setPhoneNumber("08557875452");
//        user.setRole(Role.USER.getRoleValue());
//        user.setPassword("65465465465");
//
//        userDAO.createUser(user);


        List<User> list = userDAO.getAllUsers();

        for (User user1 : list){
            System.out.println(user1.toString());
        }


    }
}
