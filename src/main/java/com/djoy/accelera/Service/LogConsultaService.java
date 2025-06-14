package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.LogConsultaEntity;
import com.djoy.accelera.Repository.LogConsultaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class LogConsultaService {

    @SuppressWarnings("unused")

    private final LogConsultaRepository logConsultaRepository;

    /*====Incluir===*/
    public LogConsultaEntity incluir(LogConsultaEntity logConsulta){
        return logConsultaRepository.save(logConsulta);
    }

    /*====Editar===*/
    public LogConsultaEntity editar(int id, LogConsultaEntity logConsulta){
        //Verifica se o registro existe
        Optional<LogConsultaEntity> logConsultaExistente = logConsultaRepository.findById(id);

        if (logConsultaExistente.isPresent()) {
            //Atualiza o registro
            LogConsultaEntity logConsultaAtualizada = logConsultaExistente.get();

            // Atualiza os campos necessários
            logConsultaAtualizada.setConsulta(logConsulta.getConsulta());
            logConsultaAtualizada.setDataAlteracao(LocalDateTime.now());
            logConsultaAtualizada.setObservacaoNova(logConsultaAtualizada.getObservacaoNova());         
            logConsultaAtualizada.setStatusNovo(logConsultaAtualizada.getStatusNovo());
            logConsultaAtualizada.setUsuarioAlteracao(logConsultaAtualizada.getUsuarioAlteracao());
            logConsultaAtualizada.setVinculoNovo(logConsultaAtualizada.getVinculoNovo());

            // Salva o registro atualizado
            return logConsultaRepository.save(logConsultaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<LogConsultaEntity> listarTodos(){
        return logConsultaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        logConsultaRepository.deleteById(id);
    }    


}
