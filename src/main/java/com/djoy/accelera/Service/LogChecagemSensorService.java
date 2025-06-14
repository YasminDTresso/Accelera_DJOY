package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.LogChecagemSensorEntity;
import com.djoy.accelera.Repository.LogChecagemSensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class LogChecagemSensorService {

    @SuppressWarnings("unused")

    private final LogChecagemSensorRepository logChecagemSensorRepository;

    /*====Incluir===*/
    public LogChecagemSensorEntity incluir(LogChecagemSensorEntity logChecagemSensor){
        return logChecagemSensorRepository.save(logChecagemSensor);
    }

    /*====Editar===*/
    public LogChecagemSensorEntity editar(int id, LogChecagemSensorEntity logChecagemSensor){
        //Verifica se o registro existe
        Optional<LogChecagemSensorEntity> logChecagemSensorExistente = logChecagemSensorRepository.findById(id);

        if (logChecagemSensorExistente.isPresent()) {
            //Atualiza o registro
            LogChecagemSensorEntity logChecagemSensorAtualizada = logChecagemSensorExistente.get();

            // Atualiza os campos necessários
            logChecagemSensorAtualizada.setChecagemSensor(logChecagemSensor.getChecagemSensor());
            logChecagemSensorAtualizada.setDataAlteracao(LocalDateTime.now());
            logChecagemSensorAtualizada.setEquipamentoNovo(logChecagemSensor.getEquipamentoNovo());
            logChecagemSensorAtualizada.setGestor(logChecagemSensor.getGestor());
            logChecagemSensorAtualizada.setInicioProblemaNovo(logChecagemSensor.getInicioProblemaNovo());
            logChecagemSensorAtualizada.setObservacaoNova(logChecagemSensor.getObservacaoNova());
            logChecagemSensorAtualizada.setProblemaEquipamentoNovo(logChecagemSensor.getProblemaEquipamentoNovo());
            logChecagemSensorAtualizada.setStatusNovo(logChecagemSensor.getStatusNovo());
            logChecagemSensorAtualizada.setTransportadora(logChecagemSensor.getTransportadora());
            logChecagemSensorAtualizada.setUsuarioAlteracao(logChecagemSensor.getUsuarioAlteracao());
            logChecagemSensorAtualizada.setValidadeNova(logChecagemSensor.getValidadeNova());
            logChecagemSensorAtualizada.setVeiculoNovo(logChecagemSensor.getVeiculoNovo());
            logChecagemSensorAtualizada.setVinculoNovo(logChecagemSensor.getVinculoNovo());

            // Salva o registro atualizado
            return logChecagemSensorRepository.save(logChecagemSensorAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<LogChecagemSensorEntity> listarTodos(){
        return logChecagemSensorRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        logChecagemSensorRepository.deleteById(id);
    }    

}
