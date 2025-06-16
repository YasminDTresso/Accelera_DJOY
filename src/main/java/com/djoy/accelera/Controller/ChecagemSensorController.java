package com.djoy.accelera.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.DTO.InserirChecagemSensorDTO;
import com.djoy.accelera.DTO.InserirConsultaDTO;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.ChecagemSensorRepository;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.ChecagemSensorService;
import com.djoy.accelera.Service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/checagem-sensor")
public class ChecagemSensorController {

    @Autowired
    private final ChecagemSensorService checagemSensorService;

    @Autowired
    private final ChecagemSensorRepository checagemSensorRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    /*================Listar Checagens Ativas================*/
        /*================Incluir================*/
    @PostMapping("/nova-checagem")
    public ResponseEntity incluir(@RequestBody @Valid InserirChecagemSensorDTO data){

    //Buscando o usuário logado
    UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    checagemSensorService.incluir(usuario, data.transportadora(), data.veiculo(), data.gestor(), data.validade(), data.inicioProblema(), data.equipamento(), data.problemaEquipamento(), data.observacao(), data.status(), data.vinculo());

    return ResponseEntity.ok().build();
    }
}
