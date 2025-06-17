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

import com.djoy.accelera.DTO.EditarAgendaDTO;
import com.djoy.accelera.DTO.InserirAgendaDTO;
import com.djoy.accelera.Entity.AgendaEntity;
import com.djoy.accelera.Entity.Enum.statusRota;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.AgendaRepository;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.AgendaService;
import com.djoy.accelera.Service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/agenda")
public class AgendaController {


    @Autowired
    private final AgendaService agendaService;

    @Autowired
    private final AgendaRepository agendaRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    /*================Listar Consultas Ativas================*/
    @GetMapping
    public ResponseEntity<List<AgendaEntity>> listar(){
        //Listando todas as Agendas menos as canceladas
        List<AgendaEntity> lista = agendaRepository.findAllExcludingCertainStatus(statusRota.CANCELADA);
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping("/nova-agenda")
    public ResponseEntity incluir(@RequestBody @Valid InserirAgendaDTO data){

    //Buscando o usuário logado
    UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    agendaService.incluir(usuario, data.transportadora(), data.condutor(), data.veiculo(), data.consulta(), data.checagemSensor(), data.observacao(), data.rota(), data.sinalBRRISK(), data.sinalTCELL(), data.SM());

    return ResponseEntity.ok().build();
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<AgendaEntity> editar(@PathVariable int id, @RequestBody @Valid EditarAgendaDTO data){

    //Buscando e armazenando o usuário logado        
    UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
  
   //Buscando e armazenando a agenda ser editada         
    AgendaEntity agenda = agendaRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));        

    AgendaEntity agendaAtualizada = agendaService.editar(agenda, usuario, data.condutor(), data.veiculo(), data.consulta(), data.checagemSensor(), data.observacao(), data.rota(),
                                                         data.sinalBRRISK(), data.sinalTCELL(), data. SM());

    return ResponseEntity.ok(agendaAtualizada);
    
    }

    /*================Excluir================*/
    @PutMapping("/excluir/{id}")
    public ResponseEntity<AgendaEntity> excluir(@PathVariable int id){

        //Buscando e armazenando o usuário logado        
        UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        //Buscando e armazenando a Agenda ser deletada         
        AgendaEntity agenda = agendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));        

        //Guardando a Agenda para exibir após 'exclusão'
        AgendaEntity agendaExcluida = agenda;

        agendaService.excluir(id, usuario);

        return ResponseEntity.ok(agendaExcluida);
    
    }
}
