package kz.bitlab.group22.controllers;

import kz.bitlab.group22.beans.UserBean;
import kz.bitlab.group22.entities.Roles;
import kz.bitlab.group22.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserBean userBean;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/enter")
    public String login(){
        return "login";
    }

    @GetMapping(path = "/profile")
    public String profile(Model model){
        model.addAttribute("user", getUserData());
        return "profile";
    }

    @PostMapping(path = "/register")
    public String toRegister(
            @RequestParam(name = "user_email") String userEmail,
            @RequestParam(name = "user_password") String userPass,
            @RequestParam(name = "re_user_password") String reUserPass,
            @RequestParam(name = "user_full_name") String userFull

    ){

        Users check = userBean.getUserByEmail(userEmail);
        if(check==null){

            if(userPass.equals(reUserPass)){

                Roles roleUser = userBean.getRole(3L);
                List<Roles> roles = new ArrayList<>();
                roles.add(roleUser);
                Users user = new Users(userEmail, passwordEncoder.encode(userPass), userFull, roles);

                userBean.addUser(user);

            }

        }
        return "redirect:/enter";
    }

    @GetMapping(path = "/registration")
    public String registration(Model model){
        model.addAttribute("user", getUserData());
        return "registration";
    }

    public Users getUserData(){
        Users user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User ud = (User) authentication.getPrincipal();
            user = userBean.getUserByEmail(ud.getUsername());
        }
        return user;
    }

}
