package kz.bitlab.group22.services;

import kz.bitlab.group22.beans.UserBean;
import kz.bitlab.group22.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserBean userBean;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users user = userBean.getUserByEmail(s);
        User securityUser = new User(user.getEmail(), user.getPassword(), user.getRoles());
        return securityUser;

    }
}
