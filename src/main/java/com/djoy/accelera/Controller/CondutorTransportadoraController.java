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

import com.djoy.accelera.Entity.CondutorTransportadoraEntity;
import com.djoy.accelera.Entity.Key.CondutorTransportadoraKey;
import com.djoy.accelera.Service.CondutorTransportadoraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/condutor-transportadora")
public class CondutorTransportadoraController {

    @SuppressWarnings("unused")
    private final CondutorTransportadoraService condutorTransportadoraService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<CondutorTransportadoraEntity>> listarTodos(){
        List<CondutorTransportadoraEntity> lista = condutorTransportadoraService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<CondutorTransportadoraEntity> incluir(@RequestBody CondutorTransportadoraEntity condutorTransportadora){
        CondutorTransportadoraEntity novo = condutorTransportadoraService.incluir(condutorTransportadora);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<CondutorTransportadoraEntity> editar(@PathVariable CondutorTransportadoraKey id, 
    @RequestBody CondutorTransportadoraEntity CondutorTransportadora){
        CondutorTransportadoraEntity atualizado = condutorTransportadoraService.editar(id, CondutorTransportadora);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable CondutorTransportadoraKey id) {
        condutorTransportadoraService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

}
