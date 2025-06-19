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

import com.djoy.accelera.DTO.EditarVeiculoDTO;
import com.djoy.accelera.DTO.InserirVeiculoDTO;
import com.djoy.accelera.Entity.AgendaEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Repository.VeiculoRepository;
import com.djoy.accelera.Service.VeiculoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    @SuppressWarnings("unused")
    private final VeiculoService veiculoService;

    private final VeiculoRepository veiculoRepository;

    /*================Listar Todos================*/
    @GetMapping
    public ResponseEntity<List<VeiculoEntity>> listarTodos(){
        List<VeiculoEntity> lista = veiculoService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }


    /*================Incluir================*/    
    @PostMapping("/novo-veiculo")
    public ResponseEntity<VeiculoEntity> incluir(@RequestBody @Valid InserirVeiculoDTO data){

        VeiculoEntity veiculo = new VeiculoEntity();

        veiculo.setModelo(data.modelo());
        veiculo.setPlaca(data.placa());
        veiculo.setPlacaCarreta(data.placaCarreta());
        veiculo.setProprietario(data.proprietario());
        veiculo.setChassi(data.chassi());
        veiculo.setCor(data.cor());

        veiculoService.incluir(veiculo);

        return ResponseEntity.ok(veiculo);
    }

    /*================Editar================*/
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoEntity> editar(@PathVariable int id, @RequestBody @Valid EditarVeiculoDTO data){

        VeiculoEntity veiculoAtualizado = veiculoService.editar(id, data);

        if(veiculoAtualizado != null){
            return new ResponseEntity<>(veiculoAtualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*================Excluir================*/
    @DeleteMapping("/{id}")
    public ResponseEntity<VeiculoEntity> excluir(@PathVariable Integer id) {

        //Buscando e armazenando o veiculo a ser deletado        
        VeiculoEntity veiculoExcluido = veiculoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));  

        veiculoService.excluir(id);

        return ResponseEntity.ok(veiculoExcluido);
       
    } 

}
