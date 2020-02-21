package kz.bitlab.group22.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @GetMapping(path = "/index")
    public String index(Model model){
        return "admin/index";
    }

}
