package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum Role {

    DRIVER("DRIVER"),

    DISPATCHER("DISPATCHER"),

    ADMIN("ADMIN"),

    USER("USER");

    private final String roleValue;

    Role(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public static List<Role> getListRole(){
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.DISPATCHER);
        roles.add(Role.DRIVER);
        roles.add(Role.USER);
        return roles;
    }

    public static Optional<Role> findRole(String roleString){
        Optional<Role> role = null;
        return role = getListRole()
                .stream()
                .filter(s -> s.getRoleValue().equals(roleString))
                .findAny();
    }



}
