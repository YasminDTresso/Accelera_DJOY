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

import br.accelera.djoy.accelera.Entities.Log_Trabalho_PendenteEntity;
import br.accelera.djoy.accelera.Services.Log_Trabalho_PendenteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/log-trabalho-pendente")
public class Log_Trabalho_PendenteController {

    @SuppressWarnings("unused")
    private final Log_Trabalho_PendenteService logTrabalhoPendenteService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<Log_Trabalho_PendenteEntity>> listarTodos(){
        List<Log_Trabalho_PendenteEntity> lista = logTrabalhoPendenteService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping

    public ResponseEntity<Log_Trabalho_PendenteEntity> incluir(@RequestBody Log_Trabalho_PendenteEntity logTrabalhoPendente){
       Log_Trabalho_PendenteEntity novo = logTrabalhoPendenteService.incluir(logTrabalhoPendente);
       
        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }  

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<Log_Trabalho_PendenteEntity> editar(@PathVariable int id, 
    @RequestBody Log_Trabalho_PendenteEntity logTrabalhoPendente){
        Log_Trabalho_PendenteEntity atualizado = logTrabalhoPendenteService.editar(id, logTrabalhoPendente);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        logTrabalhoPendenteService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   

}
