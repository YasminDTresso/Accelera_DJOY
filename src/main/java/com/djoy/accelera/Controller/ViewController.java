package com.djoy.accelera.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/auth/view-login")
    public String login() {
        return "login"; // vai carregar templates/login.html
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}

