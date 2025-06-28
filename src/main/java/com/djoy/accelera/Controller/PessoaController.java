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

import com.djoy.accelera.DTO.EditarPessoaDTO;
import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Service.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @SuppressWarnings("unused")
    private final PessoaService pessoaService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<PessoaEntity>> listarTodos(){
        List<PessoaEntity> lista = pessoaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<PessoaEntity> incluir(@RequestBody PessoaEntity pessoa){
        PessoaEntity novo = pessoaService.incluir(pessoa);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<PessoaEntity> editar(@PathVariable int id, 
    @RequestBody EditarPessoaDTO pessoa){
        PessoaEntity atualizado = pessoaService.editar(id, pessoa);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        pessoaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  
}
