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

import com.djoy.accelera.Entity.AgendaEntity;
import com.djoy.accelera.Service.AgendaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/agenda")
public class AgendaController {

    @SuppressWarnings("unused")
    private final AgendaService agendaService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<AgendaEntity>> listarTodos(){
        List<AgendaEntity> lista = agendaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<AgendaEntity> incluir(@RequestBody AgendaEntity agenda){
        AgendaEntity novo = agendaService.incluir(agenda);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<AgendaEntity> editar(@PathVariable int id, 
    @RequestBody AgendaEntity agenda){
        AgendaEntity atualizado = agendaService.editar(id, agenda);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        agendaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }     
}
