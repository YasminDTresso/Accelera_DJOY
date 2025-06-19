package com.djoy.accelera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.DTO.EditarTransportadoraDTO;
import com.djoy.accelera.DTO.InserirTransportadoraDTO;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Repository.TransportadoraRepository;
import com.djoy.accelera.Service.TransportadoraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/transportadora")
public class TransportadoraController {

    @SuppressWarnings("unused")
    private final TransportadoraService transportadoraService;

    private final TransportadoraRepository transportadoraRepository;

    /*================Listar Transportadoras Ativas================*/
    @GetMapping
    public ResponseEntity<List<TransportadoraEntity>> listar(){

         //Listando todos os condutores menos os inativos
        List<TransportadoraEntity> lista = transportadoraRepository.findByRegistroAtivoTrue();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping("/nova-transportadora")
    public ResponseEntity<TransportadoraEntity> incluir(@RequestBody @Valid InserirTransportadoraDTO data) {

        TransportadoraEntity transportadora = transportadoraService.incluir(data.cnpj(), data.dataNascimento(), data.telefone(), data.nome(), data.nomeFantasia(), data.email(), data.tipoServico(), data.tipoVinculo());

        return ResponseEntity.ok(transportadora);
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<TransportadoraEntity> editar(@PathVariable int id, 
    @RequestBody EditarTransportadoraDTO data){
        
        TransportadoraEntity transportadora = transportadoraRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));

        TransportadoraEntity transportadoraEditada = transportadoraService.editar(data.cnpj(), data.dataNascimento(), data.telefone(), data.nome(), data.nomeFantasia(), data.email(), data.tipoServico(), data.tipoVinculo());

        return ResponseEntity.ok(transportadoraEditada);
    }

    /*================Excluir================*/
    @PutMapping("/excluir/{id}")
    public ResponseEntity<TransportadoraEntity> excluir(@PathVariable int id){
    
        //Buscando e armazenando o Transportadora a ser deletado         
        TransportadoraEntity transportadora = transportadoraRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transportadora não encontrado"));        

        //Guardando o Transportadora para exibir após 'exclusão'
        TransportadoraEntity transportadoraExcluida = transportadora;

        transportadoraService.excluir(id, transportadora);

        return ResponseEntity.ok(transportadoraExcluida);
    
    }


}
