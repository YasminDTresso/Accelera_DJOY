package com.djoy.accelera.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("view/auth/login")
    public String login() {
        return "login"; // vai carregar templates/login.html
    }

    @GetMapping("view/home")
    public String home() {
        return "home";
    }

    @GetMapping("view/transportadora/cadastro")
    public String cadastroTransportadora() {
        return "cadastro_transportadora";
    }

    @GetMapping("view/transportadora/edicao")
    public String edicaoTransportadora() {
        return "editar_transportadora";
    }

    @GetMapping("view/transportadora/exclusao")
    public String exclusaoTransportadora() {
        return "deletar_transportadora";
    }

    @GetMapping("view/checagem-sensor")
    public String checagemSensor() {
        return "checagem-sensor";
    }

}

