package com.djoy.accelera.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.CondutorTransportadoraEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Key.CondutorTransportadoraKey;
import com.djoy.accelera.Repository.CondutorTransportadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class CondutorTransportadoraService {

    @SuppressWarnings("unused")

    private final CondutorTransportadoraRepository condutorTransportadoraRepository;

    /*====Incluir===*/
    public CondutorTransportadoraEntity incluir(CondutorTransportadoraEntity condutorTransportadora){

        return condutorTransportadoraRepository.save(condutorTransportadora);

    }

    /*====Listar Todos===*/
    public List<CondutorTransportadoraEntity> listarTodos(){
        return condutorTransportadoraRepository.findAll();
    }

        /*====Deletar===*/
    public void excluir(CondutorTransportadoraKey id){
        condutorTransportadoraRepository.deleteById(id);
    } 

}
