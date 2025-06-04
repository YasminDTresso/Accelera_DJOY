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

import br.accelera.djoy.accelera.Entities.UsuarioTransportadoraEntity;
import br.accelera.djoy.accelera.Entities.UsuarioTransportadoraKey;
import br.accelera.djoy.accelera.Services.UsuarioTransportadoraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/usuario-transportadora")
public class UsuarioTransportadoraController {

     @SuppressWarnings("unused")
    private final UsuarioTransportadoraService usuarioTransportadoraService;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<UsuarioTransportadoraEntity>> listarTodos(){
        List<UsuarioTransportadoraEntity> lista = usuarioTransportadoraService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
    
    /*================Incluir================*/
    @PostMapping
    public ResponseEntity<UsuarioTransportadoraEntity> incluir(@RequestBody UsuarioTransportadoraEntity usuarioTransportadora){
        UsuarioTransportadoraEntity novo = usuarioTransportadoraService.incluir(usuarioTransportadora);

        if(novo != null){
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioTransportadoraEntity> editar(@PathVariable UsuarioTransportadoraKey id, 
    @RequestBody UsuarioTransportadoraEntity usuarioTransportadora){
        UsuarioTransportadoraEntity atualizado = usuarioTransportadoraService.editar(id, usuarioTransportadora);

        if(atualizado != null){
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UsuarioTransportadoraKey id) {
        usuarioTransportadoraService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   

}
