package com.zahangir.spring_security_db.controller;

import com.zahangir.spring_security_db.model.User;
import com.zahangir.spring_security_db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Zahangir Alam on 2018-07-27.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public String login(Model model, @RequestParam(value = "error", required = false) String error){
        if(error !=null){
            model.addAttribute("result", "Email or password incorrect. Please try again");
        }else {
            model.addAttribute("result", "");
        }
        return "login";
    }

    @GetMapping("/admin/home")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("name", "Hello, " + user.getName() + ", Welcome to Spring Security");
        return "home";
    }

}
