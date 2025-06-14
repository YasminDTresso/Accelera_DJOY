package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.CondutorTransportadoraEntity;
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

    /*====Editar===*/
    public CondutorTransportadoraEntity editar(CondutorTransportadoraKey id, CondutorTransportadoraEntity condutorTransportadora){
        //Verifica se o registro existe
        Optional<CondutorTransportadoraEntity> condutorTransportadoraExistente = condutorTransportadoraRepository.findById(id);

        if (condutorTransportadoraExistente.isPresent()) {
            //Atualiza o registro
            CondutorTransportadoraEntity condutorTransportadoraAtualizado = condutorTransportadoraExistente.get();

            // Atualiza os campos necessários
            condutorTransportadoraAtualizado.setId(condutorTransportadora.getId());

            // Salva o registro atualizado
            return condutorTransportadoraRepository.save(condutorTransportadoraAtualizado);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

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
