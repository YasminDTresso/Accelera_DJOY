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

import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.PessoaJuridicaEntity;
import com.djoy.accelera.Service.PessoaJuridicaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/pessoa-juridica")
public class PessoaJuridicaController {

    @SuppressWarnings("unused")
    private final PessoaJuridicaService pessoaJuridicaService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<PessoaJuridicaEntity>> listarTodos(){
        List<PessoaJuridicaEntity> lista = pessoaJuridicaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<PessoaJuridicaEntity> incluir(@RequestBody PessoaJuridicaEntity pessoaJuridica){
        PessoaJuridicaEntity novo = pessoaJuridicaService.incluir(pessoaJuridica);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<PessoaJuridicaEntity> editar(@PathVariable PessoaEntity id, 
    @RequestBody PessoaJuridicaEntity PessoaJuridica){
        PessoaJuridicaEntity atualizado = pessoaJuridicaService.editar(id, PessoaJuridica);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable PessoaEntity id) {
        pessoaJuridicaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   

}
