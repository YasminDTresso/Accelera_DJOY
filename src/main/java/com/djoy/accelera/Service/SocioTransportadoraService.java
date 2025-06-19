package com.djoy.accelera.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.SocioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.SocioTransportadoraKey;
import com.djoy.accelera.Repository.SocioTransportadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class SocioTransportadoraService {

    @SuppressWarnings("unused")

    private final SocioTransportadoraRepository socioTransportadoraRepository;

    /*====Incluir===*/
    public SocioTransportadoraEntity incluir(SocioTransportadoraEntity socioTransportadora){

        return socioTransportadoraRepository.save(socioTransportadora);

    }

    /*====Listar Todos===*/
    public List<SocioTransportadoraEntity> listarTodos(){
        return socioTransportadoraRepository.findAll();
    }

        /*====Deletar===*/
    public void excluir(SocioTransportadoraKey id){
        socioTransportadoraRepository.deleteById(id);
    } 

}
