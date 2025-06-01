package com.djoy.accelera.Service;

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
            logChecagemSensorAtualizada.setDataAlteracao(logChecagemSensor.getDataAlteracao());
            // logChecagemSensorAtualizada.setEquipamentoAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setEquipamentoNovo(logChecagemSensor.getEquipamentoNovo());
            logChecagemSensorAtualizada.setGestor(logChecagemSensor.getGestor());
            // logChecagemSensorAtualizada.setInicioProblemaAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setInicioProblemaNovo(logChecagemSensor.getInicioProblemaNovo());
            // logChecagemSensorAtualizada.setObservacaoAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setObservacaoNova(logChecagemSensor.getObservacaoNova());
            // logChecagemSensorAtualizada.setProblemaEquipamentoAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setProblemaEquipamentoNovo(logChecagemSensor.getProblemaEquipamentoNovo());
            // logChecagemSensorAtualizada.setStatusAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setStatusNovo(logChecagemSensor.getStatusNovo());
            logChecagemSensorAtualizada.setTransportadora(logChecagemSensor.getTransportadora());
            logChecagemSensorAtualizada.setUsuarioAlteracao(logChecagemSensor.getUsuarioAlteracao());
            // logChecagemSensorAtualizada.setValidadeAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setValidadeNova(logChecagemSensor.getValidadeNova());
            // logChecagemSensorAtualizada.setVeiculoAnterior(logChecagemSensor.get);
            logChecagemSensorAtualizada.setVeiculoNovo(logChecagemSensor.getVeiculoNovo());
            // logChecagemSensorAtualizada.setVinculoAnterior(logChecagemSensor.get);
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
