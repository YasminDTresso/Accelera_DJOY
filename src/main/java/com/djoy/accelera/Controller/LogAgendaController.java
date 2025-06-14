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

import com.djoy.accelera.Entity.LogAgendaEntity;
import com.djoy.accelera.Service.LogAgendaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/log-agenda")
public class LogAgendaController {

    @SuppressWarnings("unused")
    private final LogAgendaService logAgendaService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<LogAgendaEntity>> listarTodos(){
        List<LogAgendaEntity> lista = logAgendaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<LogAgendaEntity> incluir(@RequestBody LogAgendaEntity logAgenda){
        LogAgendaEntity novo = logAgendaService.incluir(logAgenda);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<LogAgendaEntity> editar(@PathVariable int id, 
    @RequestBody LogAgendaEntity logAgenda){
        LogAgendaEntity atualizado = logAgendaService.editar(id, logAgenda);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        logAgendaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }       

}
