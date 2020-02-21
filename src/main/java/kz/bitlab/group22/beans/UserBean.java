package kz.bitlab.group22.beans;

import kz.bitlab.group22.entities.Roles;
import kz.bitlab.group22.entities.Users;

public interface UserBean {

    Users getUserByEmail(String email);
    void addUser(Users user);
    Roles getRole(Long id);

}
