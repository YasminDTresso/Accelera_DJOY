package com.djoy.accelera.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.Entity.PessoaJuridicaEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Service.TransportadoraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/transportadora")
public class TransportadoraController {

    @SuppressWarnings("unused")
    private final TransportadoraService transportadoraService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<TransportadoraEntity>> listarTodos(){
        List<TransportadoraEntity> lista = transportadoraService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<TransportadoraEntity> incluir(@RequestBody TransportadoraEntity transportadora){
        TransportadoraEntity novo = transportadoraService.incluir(transportadora);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<TransportadoraEntity> editar(@PathVariable PessoaJuridicaEntity id, 
    @RequestBody TransportadoraEntity transportadora){
        TransportadoraEntity atualizado = transportadoraService.editar(id, transportadora);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable PessoaJuridicaEntity id) {
        transportadoraService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

}
