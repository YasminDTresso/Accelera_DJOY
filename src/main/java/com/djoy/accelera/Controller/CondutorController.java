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

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Service.CondutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/condutor")
public class CondutorController {

    @SuppressWarnings("unused")
    private final CondutorService condutorService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<CondutorEntity>> listarTodos(){
        List<CondutorEntity> lista = condutorService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<CondutorEntity> incluir(@RequestBody CondutorEntity condutor){
        CondutorEntity novo = condutorService.incluir(condutor);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<CondutorEntity> editar(@PathVariable PessoaFisicaEntity id, 
    @RequestBody CondutorEntity condutor){
        CondutorEntity atualizado = condutorService.editar(id, condutor);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable PessoaFisicaEntity id) {
        condutorService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

}
