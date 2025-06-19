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

import com.djoy.accelera.DTO.InserirSocioTransportadoraDTO;
import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.SocioTransportadoraEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.Key.SocioTransportadoraKey;
import com.djoy.accelera.Repository.PessoaRepository;
import com.djoy.accelera.Repository.SocioTransportadoraRepository;
import com.djoy.accelera.Repository.TransportadoraRepository;
import com.djoy.accelera.Service.SocioTransportadoraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/socio-transportadora")
public class SocioTransportadoraController {

    @SuppressWarnings("unused")
    private final SocioTransportadoraService socioTransportadoraService;

    private final SocioTransportadoraRepository socioTransportadoraRepository;
    private final PessoaRepository pessoaRepository;
    private final TransportadoraRepository transportadoraRepository;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<SocioTransportadoraEntity>> listarTodos(){
    List<SocioTransportadoraEntity> lista = socioTransportadoraService.listarTodos();
    return ResponseEntity.ok().body(lista);
    }


    /*================Incluir================*/    
    @PostMapping("/nova-associacao")
    public ResponseEntity<SocioTransportadoraEntity> incluir(@RequestBody @Valid InserirSocioTransportadoraDTO data){

        PessoaEntity pessoa = pessoaRepository.findById(data.idPessoa())
        .orElseThrow(() -> new RuntimeException("Socio não encontrado"));

        TransportadoraEntity transportadora = transportadoraRepository.findById(data.idTransportadora())
        .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

        SocioTransportadoraKey chave = new SocioTransportadoraKey();
        chave.setPessoaId(pessoa);
        chave.setTransportadoraId(transportadora);

        SocioTransportadoraEntity socioTransportadora = new SocioTransportadoraEntity();
        socioTransportadora.setId(chave);

        socioTransportadoraService.incluir(socioTransportadora);

        return ResponseEntity.ok(socioTransportadora);

    }

    /*================Excluir================*/
    @DeleteMapping("/excluir")
    public ResponseEntity<SocioTransportadoraEntity> excluir(
    @RequestParam int idPessoa,
    @RequestParam int idTransportadora
    ) {
    PessoaEntity pessoa = pessoaRepository.findById(idPessoa)
    .orElseThrow(() -> new RuntimeException("Socio não encontrado"));

    TransportadoraEntity transportadora = transportadoraRepository.findById(idTransportadora)
    .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

    SocioTransportadoraKey chave = new SocioTransportadoraKey();
    chave.setPessoaId(pessoa);
    chave.setTransportadoraId(transportadora);

    // Buscar a associação antes de deletar
    SocioTransportadoraEntity socioTransportadora = socioTransportadoraRepository.findById(chave)
    .orElseThrow(() -> new RuntimeException("Associação não encontrada"));

    // Excluir
    socioTransportadoraService.excluir(chave);

    return ResponseEntity.ok(socioTransportadora);
    }

}
