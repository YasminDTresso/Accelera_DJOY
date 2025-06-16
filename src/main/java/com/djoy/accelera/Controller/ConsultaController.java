package com.djoy.accelera.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.DTO.EditarConsultaDTO;
import com.djoy.accelera.DTO.InserirConsultaDTO;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.ConsultaRepository;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.ConsultaService;
import com.djoy.accelera.Service.UsuarioService;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/consulta")
public class ConsultaController {

    @Autowired
    private final ConsultaService consultaService;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ConsultaRepository consultaRepository;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<ConsultaEntity>> listarTodos(){
        List<ConsultaEntity> lista = consultaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping("/nova-consulta")
    public ResponseEntity incluir(@RequestBody @Valid InserirConsultaDTO data){

    //Buscando o usuário logado
    UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    consultaService.incluir(usuario, data.transportadora(), data.condutor(), data.veiculo(), data.validade(), data.observacao(), data.status(), data.vinculo());

    return ResponseEntity.ok().build();
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaEntity> editar(@PathVariable int id, @RequestBody @Valid EditarConsultaDTO data){

    //Buscando e armazenando o usuário logado        
    UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
  
   //Buscando e armazenando a consulta ser editada         
    ConsultaEntity consulta = consultaRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));        

    ConsultaEntity consultaAtualizada = consultaService.editar(consulta, data.condutor(), usuario, data.veiculo(), data.validade(), data.observacao(), data.status(), data.vinculo());

    return ResponseEntity.ok(consultaAtualizada);
    
    }
}
