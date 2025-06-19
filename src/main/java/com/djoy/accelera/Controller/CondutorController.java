package com.djoy.accelera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.DTO.InserirCondutorDTO;
import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Repository.CondutorRepository;
import com.djoy.accelera.Service.CondutorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/condutor")
public class CondutorController {

    @SuppressWarnings("unused")
    private final CondutorService condutorService;

    private final CondutorRepository condutorRepository;


    /*================Listar Condutores Ativos================*/
    @GetMapping
    public ResponseEntity<List<CondutorEntity>> listar(){

         //Listando todos os condutores menos os inativos
        List<CondutorEntity> lista = condutorRepository.findByRegistroAtivoTrue();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping("/novo-condutor")
    public ResponseEntity<CondutorEntity> incluir(@RequestBody @Valid InserirCondutorDTO data) {

        // Recupera o condutor recém-criado por cnh
        CondutorEntity condutor = condutorService.incluir(data.cpf(), data.cnh(), data.dataNascimento(), data.telefone(), data.nome(), data.email());

        return ResponseEntity.ok(condutor);
    }


    /*================Excluir================*/
    @PutMapping("/excluir/{id}")
    public ResponseEntity<CondutorEntity> excluir(@PathVariable int id){
    
        //Buscando e armazenando o condutor a ser deletado         
        CondutorEntity condutor = condutorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Condutor não encontrado"));        

        //Guardando o condutor para exibir após 'exclusão'
        CondutorEntity condutorExcluido = condutor;

        condutorService.excluir(id, condutorExcluido);

        return ResponseEntity.ok(condutorExcluido);
    
    }
    

}
