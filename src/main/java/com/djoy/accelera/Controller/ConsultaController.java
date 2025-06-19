package com.djoy.accelera.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

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
import com.djoy.accelera.Entity.Enum.statusEtapa;

import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.ConsultaRepository;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.ConsultaService;
import com.djoy.accelera.Service.UsuarioService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/consulta")
public class ConsultaController {

    @Autowired
    private final ConsultaService consultaService;

    @Autowired
    private final ConsultaRepository consultaRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioRepository usuarioRepository;


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

    /*================Excluir================*/
    @PutMapping("/excluir/{id}")
    public ResponseEntity<ConsultaEntity> excluir(@PathVariable int id){

        //Buscando e armazenando o usuário logado        
        UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        //Buscando e armazenando a consulta ser deletada         
        ConsultaEntity consulta = consultaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));        

        //Guardando a consulta para exibir após 'exclusão'
        ConsultaEntity consultaExcluida = consulta;

        consultaService.excluir(id, usuario);

        return ResponseEntity.ok(consultaExcluida);
    
    }
}
