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

import com.djoy.accelera.Entity.LogConsultaEntity;
import com.djoy.accelera.Service.LogConsultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/log-consulta")
public class LogConsultaController {

    @SuppressWarnings("unused")
    private final LogConsultaService logConsultaService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<LogConsultaEntity>> listarTodos(){
        List<LogConsultaEntity> lista = logConsultaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<LogConsultaEntity> incluir(@RequestBody LogConsultaEntity logConsulta){
        LogConsultaEntity novo = logConsultaService.incluir(logConsulta);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<LogConsultaEntity> editar(@PathVariable int id, 
    @RequestBody LogConsultaEntity logConsulta){
        LogConsultaEntity atualizado = logConsultaService.editar(id, logConsulta);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        logConsultaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

}
