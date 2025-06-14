package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.LogAgendaEntity;
import com.djoy.accelera.Repository.LogAgendaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class LogAgendaService {

    @SuppressWarnings("unused")

    private final LogAgendaRepository logAgendaRepository;

    /*====Incluir===*/
    public LogAgendaEntity incluir(LogAgendaEntity logAgenda){
        return logAgendaRepository.save(logAgenda);
    }

    /*====Editar===*/
    public LogAgendaEntity editar(int id, LogAgendaEntity logAgenda){
        //Verifica se o registro existe
        Optional<LogAgendaEntity> logAgendaExistente = logAgendaRepository.findById(id);

        if (logAgendaExistente.isPresent()) {
            //Atualiza o registro
            LogAgendaEntity logAgendaAtualizada = logAgendaExistente.get();

            // Atualiza os campos necessários
            logAgendaAtualizada.setAgenda(logAgenda.getAgenda()); 
            logAgendaAtualizada.setChecagemSensorNovo(logAgenda.getChecagemSensorNovo());
            logAgendaAtualizada.setCondutorNovo(logAgenda.getCondutorNovo());
            logAgendaAtualizada.setConsultaNova(logAgenda.getConsultaNova());
            logAgendaAtualizada.setDataAlteracao(LocalDateTime.now());
            logAgendaAtualizada.setObservacaoNova(logAgenda.getObservacaoNova());
            logAgendaAtualizada.setRotaNova(logAgenda.getRotaNova());
            logAgendaAtualizada.setSinalBrriskNovo(logAgenda.getSinalBrriskNovo());
            logAgendaAtualizada.setSinalTcellNovo(logAgenda.getSinalTcellNovo());
            logAgendaAtualizada.setSmNovo(logAgenda.getSmNovo());
            logAgendaAtualizada.setTransportadora(logAgenda.getTransportadora());
            logAgendaAtualizada.setUsuarioAlteracao(logAgenda.getUsuarioAlteracao());
            logAgendaAtualizada.setVeiculo(logAgenda.getVeiculo());

            // Salva o registro atualizado
            return logAgendaRepository.save(logAgendaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<LogAgendaEntity> listarTodos(){
        return logAgendaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        logAgendaRepository.deleteById(id);
    }    

}
