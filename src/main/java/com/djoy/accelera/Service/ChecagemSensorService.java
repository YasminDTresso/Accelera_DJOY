package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Repository.ChecagemSensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class ChecagemSensorService {

    @SuppressWarnings("unused")

    private final ChecagemSensorRepository checagemSensorRepository;

    /*====Incluir===*/
    public ChecagemSensorEntity incluir(ChecagemSensorEntity checagemSensor){
        return checagemSensorRepository.save(checagemSensor);
    }

    /*====Editar===*/
    public ChecagemSensorEntity editar(int id, ChecagemSensorEntity checagemSensor){
        //Verifica se o registro existe
        Optional<ChecagemSensorEntity> checagemSensorExistente = checagemSensorRepository.findById(id);

        if (checagemSensorExistente.isPresent()) {
            //Atualiza o registro
            ChecagemSensorEntity checagemSensorAtualizada = checagemSensorExistente.get();

            // Atualiza os campos necessários
            checagemSensorAtualizada.setDataAlteracao(checagemSensor.getDataAlteracao());
            // checagemSensorAtualizada.setDataInclusao();
            checagemSensorAtualizada.setEquipamento(checagemSensor.getEquipamento());
            checagemSensorAtualizada.setGestor(checagemSensor.getGestor());
            checagemSensorAtualizada.setInicioProblema(checagemSensor.getInicioProblema());
            checagemSensorAtualizada.setObservacao(checagemSensor.getObservacao());
            checagemSensorAtualizada.setProblemaEquipamento(checagemSensor.getProblemaEquipamento());
            checagemSensorAtualizada.setStatus(checagemSensor.getStatus());
            checagemSensorAtualizada.setTransportadora(checagemSensor.getTransportadora());
            checagemSensorAtualizada.setUsuarioAlteracao(checagemSensor.getUsuarioAlteracao());
            checagemSensorAtualizada.setUsuarioInclusao(checagemSensor.getUsuarioInclusao());
            checagemSensorAtualizada.setValidade(checagemSensor.getValidade());
            checagemSensorAtualizada.setVeiculo(checagemSensor.getVeiculo());
            checagemSensorAtualizada.setVinculo(checagemSensor.getVinculo());

            // Salva o registro atualizado
            return checagemSensorRepository.save(checagemSensorAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<ChecagemSensorEntity> listarTodos(){
        return checagemSensorRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        checagemSensorRepository.deleteById(id);
    }

}
