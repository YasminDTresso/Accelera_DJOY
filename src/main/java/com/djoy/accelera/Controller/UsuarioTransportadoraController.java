package com.djoy.accelera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.DTO.InserirUsuarioTransportadoraDTO;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.UsuarioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.UsuarioTransportadoraKey;
import com.djoy.accelera.Repository.TransportadoraRepository;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Repository.UsuarioTransportadoraRepository;
import com.djoy.accelera.Service.UsuarioTransportadoraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/usuario-transportadora")
public class UsuarioTransportadoraController {

    @SuppressWarnings("unused")
    private final UsuarioTransportadoraService usuarioTransportadoraService;

    private final UsuarioTransportadoraRepository usuarioTransportadoraRepository;
    private final UsuarioRepository usuarioRepository;
    private final TransportadoraRepository transportadoraRepository;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<UsuarioTransportadoraEntity>> listarTodos(){
    List<UsuarioTransportadoraEntity> lista = usuarioTransportadoraService.listarTodos();
    return ResponseEntity.ok().body(lista);
    }


    /*================Incluir================*/    
    @PostMapping("/nova-associacao")
    public ResponseEntity<UsuarioTransportadoraEntity> incluir(@RequestBody @Valid InserirUsuarioTransportadoraDTO data){

        UsuarioEntity usuario = usuarioRepository.findById(data.idUsuario())
        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        TransportadoraEntity transportadora = transportadoraRepository.findById(data.idTransportadora())
        .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

        UsuarioTransportadoraKey chave = new UsuarioTransportadoraKey();
        chave.setUsuarioId(usuario);
        chave.setTransportadoraId(transportadora);

        UsuarioTransportadoraEntity usuarioTransportadora = new UsuarioTransportadoraEntity();
        usuarioTransportadora.setId(chave);

        usuarioTransportadoraService.incluir(usuarioTransportadora);

        return ResponseEntity.ok(usuarioTransportadora);

    }

    /*================Excluir================*/
    @DeleteMapping("/excluir")
    public ResponseEntity<UsuarioTransportadoraEntity> excluir(
    @RequestParam int idUsuario,
    @RequestParam int idTransportadora
    ) {
    UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
    .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

    TransportadoraEntity transportadora = transportadoraRepository.findById(idTransportadora)
    .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

    UsuarioTransportadoraKey chave = new UsuarioTransportadoraKey();
    chave.setUsuarioId(usuario);
    chave.setTransportadoraId(transportadora);

    // Buscar a associação antes de deletar
    UsuarioTransportadoraEntity usuarioTransportadora = usuarioTransportadoraRepository.findById(chave)
    .orElseThrow(() -> new RuntimeException("Associação não encontrada"));

    // Excluir
    usuarioTransportadoraService.excluir(chave);

    return ResponseEntity.ok(usuarioTransportadora);
    }    

}
