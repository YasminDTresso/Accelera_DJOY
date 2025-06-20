package com.djoy.accelera.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/auth/login")
    public String login() {
        return "login"; // vai carregar templates/login.html
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/transportadora/cadastro")
    public String cadastroTransportadora() {
        return "cadastro_transportadora";
    }

    @GetMapping("/transportadora/edicao")
    public String edicaoTransportadora() {
        return "editar_transportadora";
    }

    @GetMapping("/transportadora/exclusao")
    public String exclusaoTransportadora() {
        return "deletar_transportadora";
    }

}

