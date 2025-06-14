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

import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Service.VeiculoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    @SuppressWarnings("unused")
    private final VeiculoService veiculoService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<VeiculoEntity>> listarTodos(){
        List<VeiculoEntity> lista = veiculoService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<VeiculoEntity> incluir(@RequestBody VeiculoEntity veiculo){
        VeiculoEntity novo = veiculoService.incluir(veiculo);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoEntity> editar(@PathVariable int id, 
    @RequestBody VeiculoEntity veiculo){
        VeiculoEntity atualizado = veiculoService.editar(id, veiculo);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        veiculoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

}
