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

import com.djoy.accelera.DTO.InserirCondutorTransportadoraDTO;
import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.CondutorTransportadoraEntity;
import com.djoy.accelera.Entity.Key.CondutorTransportadoraKey;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Repository.CondutorRepository;
import com.djoy.accelera.Repository.CondutorTransportadoraRepository;
import com.djoy.accelera.Repository.TransportadoraRepository;
import com.djoy.accelera.Service.CondutorTransportadoraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/condutor-transportadora")
public class CondutorTransportadoraController {

    @SuppressWarnings("unused")
    private final CondutorTransportadoraService condutorTransportadoraService;

    private final CondutorTransportadoraRepository condutorTransportadoraRepository;
    private final CondutorRepository condutorRepository;
    private final TransportadoraRepository transportadoraRepository;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<CondutorTransportadoraEntity>> listarTodos(){
    List<CondutorTransportadoraEntity> lista = condutorTransportadoraService.listarTodos();
    return ResponseEntity.ok().body(lista);
    }


    /*================Incluir================*/    
    @PostMapping("/nova-associacao")
    public ResponseEntity<CondutorTransportadoraEntity> incluir(@RequestBody @Valid InserirCondutorTransportadoraDTO data){

        CondutorEntity condutor = condutorRepository.findById(data.idCondutor())
        .orElseThrow(() -> new RuntimeException("Condutor não encontrado"));

        TransportadoraEntity transportadora = transportadoraRepository.findById(data.idTransportadora())
        .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

        CondutorTransportadoraKey chave = new CondutorTransportadoraKey();
        chave.setCondutorId(condutor);
        chave.setTransportadoraId(transportadora);

        CondutorTransportadoraEntity condutorTransportadora = new CondutorTransportadoraEntity();
        condutorTransportadora.setId(chave);

        condutorTransportadoraService.incluir(condutorTransportadora);

        return ResponseEntity.ok(condutorTransportadora);

    }

    /*================Excluir================*/
    @DeleteMapping("/excluir")
    public ResponseEntity<CondutorTransportadoraEntity> excluir(
    @RequestParam int idCondutor,
    @RequestParam int idTransportadora
    ) {
    CondutorEntity condutor = condutorRepository.findById(idCondutor)
    .orElseThrow(() -> new RuntimeException("Condutor não encontrado"));

    TransportadoraEntity transportadora = transportadoraRepository.findById(idTransportadora)
    .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

    CondutorTransportadoraKey chave = new CondutorTransportadoraKey();
    chave.setCondutorId(condutor);
    chave.setTransportadoraId(transportadora);

    // Buscar a associação antes de deletar
    CondutorTransportadoraEntity condutorTransportadora = condutorTransportadoraRepository.findById(chave)
    .orElseThrow(() -> new RuntimeException("Associação não encontrada"));

    // Excluir
    condutorTransportadoraService.excluir(chave);

    return ResponseEntity.ok(condutorTransportadora);
    }

}
