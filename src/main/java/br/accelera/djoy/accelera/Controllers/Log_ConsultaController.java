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

import br.accelera.djoy.accelera.Entities.Log_ConsultaEntity;
import br.accelera.djoy.accelera.Services.Log_ConsultaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/log-consulta")
public class Log_ConsultaController {

    @SuppressWarnings("unused")
    private final Log_ConsultaService logConsultaService;
    
    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<Log_ConsultaEntity>> listarTodos(){
        List<Log_ConsultaEntity> lista = logConsultaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/

    @PostMapping
    public ResponseEntity<Log_ConsultaEntity> incluir(@RequestBody Log_ConsultaEntity logConsulta){
        Log_ConsultaEntity novo = logConsultaService.incluir(logConsulta);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<Log_ConsultaEntity> editar(@PathVariable int id, 
    @RequestBody Log_ConsultaEntity logConsulta){
        Log_ConsultaEntity atualizado = logConsultaService.editar(id, logConsulta);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        logConsultaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    

}
