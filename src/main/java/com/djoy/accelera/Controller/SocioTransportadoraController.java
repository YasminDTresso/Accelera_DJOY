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

import com.djoy.accelera.Entity.SocioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.SocioTransportadoraKey;
import com.djoy.accelera.Service.SocioTransportadoraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/socio-transportadora")
public class SocioTransportadoraController {

    @SuppressWarnings("unused")
    private final SocioTransportadoraService socioTransportadoraService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<SocioTransportadoraEntity>> listarTodos(){
        List<SocioTransportadoraEntity> lista = socioTransportadoraService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<SocioTransportadoraEntity> incluir(@RequestBody SocioTransportadoraEntity socioTransportadora){
        SocioTransportadoraEntity novo = socioTransportadoraService.incluir(socioTransportadora);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<SocioTransportadoraEntity> editar(@PathVariable SocioTransportadoraKey id, 
    @RequestBody SocioTransportadoraEntity SocioTransportadora){
        SocioTransportadoraEntity atualizado = socioTransportadoraService.editar(id, SocioTransportadora);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable SocioTransportadoraKey id) {
        socioTransportadoraService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

}
