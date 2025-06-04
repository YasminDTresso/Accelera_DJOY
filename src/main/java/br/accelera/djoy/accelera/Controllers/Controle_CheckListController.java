package br.accelera.djoy.accelera.Controllers;

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

import br.accelera.djoy.accelera.Entities.Controle_CheckListEntity;
import br.accelera.djoy.accelera.Services.Controle_CheckListService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/controle-checklist")
public class Controle_CheckListController {

    @SuppressWarnings("unused")
    private final Controle_CheckListService controleCheckListService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<Controle_CheckListEntity>> listarTodos(){
        List<Controle_CheckListEntity> lista = controleCheckListService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
    
    /*================Incluir================*/

    @PostMapping
    public ResponseEntity<Controle_CheckListEntity> incluir(@RequestBody Controle_CheckListEntity controleCheckList){
        Controle_CheckListEntity novo = controleCheckListService.incluir(controleCheckList);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/
    @PutMapping("/{id}")
    public ResponseEntity<Controle_CheckListEntity> editar(@PathVariable int id, 
    @RequestBody Controle_CheckListEntity controleCheckList){
        Controle_CheckListEntity atualizado = controleCheckListService.editar(id, controleCheckList);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        controleCheckListService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    

}
