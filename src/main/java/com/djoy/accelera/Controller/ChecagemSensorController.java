package com.djoy.accelera.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.DTO.EditarChecagemSensorDTO;
import com.djoy.accelera.DTO.InserirChecagemSensorDTO;
import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.Enum.etapaChecagem;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.ChecagemSensorRepository;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.ChecagemSensorService;
import com.djoy.accelera.Service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/checagem-sensor")
public class ChecagemSensorController {

    @Autowired
    private final ChecagemSensorService checagemSensorService;

    @Autowired
    private final ChecagemSensorRepository checagemSensorRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    /*================Listar Checagens Ativas================*/
    @GetMapping
    public ResponseEntity<List<ChecagemSensorEntity>> listar(){
        //Listando todas as Checagem de Sensor, menos as canceladas
        List<ChecagemSensorEntity> lista = checagemSensorRepository.findAllExcludingCertainStatus(etapaChecagem.CANCELADO);
        return ResponseEntity.ok().body(lista);
    }

    /*================Incluir================*/
    @PostMapping("/nova-checagem")
    public ResponseEntity incluir(@RequestBody @Valid InserirChecagemSensorDTO data){

        //Buscando o usuário logado
        UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        checagemSensorService.incluir(usuario, data.transportadora(), data.veiculo(), data.gestor(), data.validade(), data.inicioProblema(), data.equipamento(), data.problemaEquipamento(), data.observacao(), data.status(), data.vinculo());

        return ResponseEntity.ok().build();
    }

    /*================Editar================*/

    @PutMapping("/{id}")
    public ResponseEntity<ChecagemSensorEntity> editar(@PathVariable int id, @RequestBody @Valid EditarChecagemSensorDTO data){

        //Buscando e armazenando o usuário logado        
        UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //Buscando e armazenando a checagem a ser editada         
        ChecagemSensorEntity checagemSensor = checagemSensorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Checagem de Sensor não encontrada"));  
        
        ChecagemSensorEntity checagemSensorAtualizada = checagemSensorService.editar(checagemSensor, usuario, data.veiculo(), data.gestor(), data.validade(), data.problemaEquipamento(), data.observacao(), data.status(), data.vinculo());

        return ResponseEntity.ok(checagemSensorAtualizada);

    }

    /*================Excluir================*/
    @PutMapping("/excluir/{id}")
    public ResponseEntity<ChecagemSensorEntity> excluir(@PathVariable int id){

        //Buscando e armazenando o usuário logado        
        UsuarioEntity usuario = usuarioRepository.findById(usuarioService.getUsuarioLogadoId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //Buscando e armazenando a ChecagemSensor a ser deletada         
        ChecagemSensorEntity checagemSensor = checagemSensorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("ChecagemSensor não encontrada"));        

        //Guardando a ChecagemSensor para exibir após 'exclusão'
        ChecagemSensorEntity checagemSensorExcluida = checagemSensor;

        checagemSensorService.excluir(id, usuario);

        return ResponseEntity.ok(checagemSensorExcluida);

    }
}
