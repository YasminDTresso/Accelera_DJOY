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
import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Service.PessoaFisicaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/pessoa-fisica")
public class PessoaFisicaController {

    @SuppressWarnings("unused")
    private final PessoaFisicaService pessoaFisicaService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<PessoaFisicaEntity>> listarTodos(){
        List<PessoaFisicaEntity> lista = pessoaFisicaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<PessoaFisicaEntity> incluir(@RequestBody PessoaFisicaEntity pessoaFisica){
        PessoaFisicaEntity novo = pessoaFisicaService.incluir(pessoaFisica);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisicaEntity> editar(@PathVariable PessoaEntity id, 
    @RequestBody PessoaFisicaEntity PessoaFisica){
        PessoaFisicaEntity atualizado = pessoaFisicaService.editar(id, PessoaFisica);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable PessoaEntity id) {
        pessoaFisicaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }      

}
