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

import br.accelera.djoy.accelera.Entities.Consulta_TransportadoraEntitiy;
import br.accelera.djoy.accelera.Services.Consulta_TransportadoraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/consulta-transportadora")
public class Consulta_TransportadoraController {
    @SuppressWarnings("unused")
    private final Consulta_TransportadoraService consultaTransportadoraService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<Consulta_TransportadoraEntitiy>> listarTodos(){
        List<Consulta_TransportadoraEntitiy> lista = consultaTransportadoraService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<Consulta_TransportadoraEntitiy> incluir(@RequestBody Consulta_TransportadoraEntitiy consultaTransportadora){
        Consulta_TransportadoraEntitiy novo = consultaTransportadoraService.incluir(consultaTransportadora);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/
    @PutMapping("/{id}")
    public ResponseEntity<Consulta_TransportadoraEntitiy> editar(@PathVariable int id,
     @RequestBody Consulta_TransportadoraEntitiy consultaTransportadora){
        Consulta_TransportadoraEntitiy atualizado = consultaTransportadoraService.editar(id, consultaTransportadora);
        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        consultaTransportadoraService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
