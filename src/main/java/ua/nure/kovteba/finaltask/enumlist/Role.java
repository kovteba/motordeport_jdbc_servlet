package ua.nure.kovteba.finaltask.enumlist;

import java.util.ArrayList;
import java.util.Arrays;
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
        return Arrays.asList(Role.values());
    }

    public static Role findRole(String roleString){
        Role role = null;
        return role = getListRole()
                .stream()
                .filter(s -> s.getRoleValue().equals(roleString))
                .findAny()
                .get();
    }

}
