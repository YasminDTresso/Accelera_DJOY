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

import com.djoy.accelera.Entity.LogChecagemSensorEntity;
import com.djoy.accelera.Service.LogChecagemSensorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/log-checagem-sensor")
public class LogChecagemSensorController {

    @SuppressWarnings("unused")
    private final LogChecagemSensorService logChecagemSensorService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<LogChecagemSensorEntity>> listarTodos(){
        List<LogChecagemSensorEntity> lista = logChecagemSensorService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<LogChecagemSensorEntity> incluir(@RequestBody LogChecagemSensorEntity logChecagemSensor){
        LogChecagemSensorEntity novo = logChecagemSensorService.incluir(logChecagemSensor);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<LogChecagemSensorEntity> editar(@PathVariable int id, 
    @RequestBody LogChecagemSensorEntity logChecagemSensor){
        LogChecagemSensorEntity atualizado = logChecagemSensorService.editar(id, logChecagemSensor);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        logChecagemSensorService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

}
