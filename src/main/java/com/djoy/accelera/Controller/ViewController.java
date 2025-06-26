package com.djoy.accelera.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djoy.accelera.Entity.Projection.AgendaDetalhadaProjection;
import com.djoy.accelera.Repository.VwDetalhesAgendaRepository;

@Controller
@RequestMapping(value = "/view")
public class ViewController {

    @GetMapping("auth/login")
    public String login() {
        return "login"; // vai carregar templates/login.html
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @GetMapping("transportadora/cadastro")
    public String cadastroTransportadora() {
        return "cadastro_transportadora";
    }

    @GetMapping("transportadora/edicao")
    public String edicaoTransportadora() {
        return "editar_transportadora";
    }

    @GetMapping("transportadora/exclusao")
    public String exclusaoTransportadora() {
        return "deletar_transportadora";
    }

    @GetMapping("checagem-sensor")
    public String checagemSensor() {
        return "checagem-sensor";
    }

    @Autowired
    private VwDetalhesAgendaRepository detalhesAgendaRepository;

    @GetMapping("agenda")
    public String mostrarDetalhes(Model model) {
        List<AgendaDetalhadaProjection> detalhes = detalhesAgendaRepository.buscarTodosDetalhes();
        model.addAttribute("detalhes", detalhes);
        return "detalhes-agenda";
    }

}

