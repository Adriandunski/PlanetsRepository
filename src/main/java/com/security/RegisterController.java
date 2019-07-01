package com.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private CustomUserService customUserService;

    public RegisterController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute LoginUser loginUser) {


        try {
            UserDetails userDetails = customUserService.loadUserByUsername(loginUser.getUsername());
            return "redirect:/login";
        } catch (UsernameNotFoundException e) {
            customUserService.saveUserApp(loginUser);
            return "redirect:/login";
        }

//        if (StringUtils.hasText(userDetails.getUsername())) {
//            return "redirect:/login";
//        } else {
//            customUserService.saveUserApp(loginUser);
//            System.out.println("SAVe");
//            return "redirect:/login";
//        }
    }

    @GetMapping("/register")
    public String loginPage() {
        return "register";
    }
}
