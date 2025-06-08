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

import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Service.ChecagemSensorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/checagemsensor")
public class ChecagemSensorController {

    @SuppressWarnings("unused")
    private final ChecagemSensorService checagemSensorService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<ChecagemSensorEntity>> listarTodos(){
        List<ChecagemSensorEntity> lista = checagemSensorService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<ChecagemSensorEntity> incluir(@RequestBody ChecagemSensorEntity checagemSensor){
        ChecagemSensorEntity novo = checagemSensorService.incluir(checagemSensor);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<ChecagemSensorEntity> editar(@PathVariable int id, 
    @RequestBody ChecagemSensorEntity ChecagemSensor){
        ChecagemSensorEntity atualizado = checagemSensorService.editar(id, ChecagemSensor);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        checagemSensorService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }     

}
