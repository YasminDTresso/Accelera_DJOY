package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Entities.Log_Trabalho_PendenteEntity;
import br.accelera.djoy.accelera.Repositories.Log_Trabalho_PendenteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class Log_Trabalho_PendenteService {

    @SuppressWarnings("unused")
    @Autowired
    private final Log_Trabalho_PendenteRepository logTrabalhoPendenteRepository;

    public Log_Trabalho_PendenteEntity incluir(Log_Trabalho_PendenteEntity logTrabalhoPendente){
        return logTrabalhoPendenteRepository.save(logTrabalhoPendente);
    }

    public Log_Trabalho_PendenteEntity editar(int id, Log_Trabalho_PendenteEntity logTrabalhoPendente){

        //Verifica se o registro existe
        Optional<Log_Trabalho_PendenteEntity> logTrabalhoPendenteExistente = logTrabalhoPendenteRepository.findById(id);

        if (logTrabalhoPendenteExistente.isPresent()) {
            //Atualiza o registro
            Log_Trabalho_PendenteEntity logTrabalhoPendenteAtualizado = logTrabalhoPendenteExistente.get();

            //Atualiza os campos necessários
            logTrabalhoPendenteAtualizado.setCampoAlterado(logTrabalhoPendente.getCampoAlterado());
            logTrabalhoPendenteAtualizado.setCheckList(logTrabalhoPendente.getCheckList());
            logTrabalhoPendenteAtualizado.setCondutor(logTrabalhoPendente.getCondutor());
            logTrabalhoPendenteAtualizado.setConsulta(logTrabalhoPendente.getConsulta());
            logTrabalhoPendenteAtualizado.setDataAlteracao(logTrabalhoPendente.getDataAlteracao());
            logTrabalhoPendenteAtualizado.setObservacao(logTrabalhoPendente.getObservacao());
            logTrabalhoPendenteAtualizado.setPlacaCarreta(logTrabalhoPendente.getPlacaCarreta());
            logTrabalhoPendenteAtualizado.setPlacaVeiculo(logTrabalhoPendente.getPlacaVeiculo());
            logTrabalhoPendenteAtualizado.setRota(logTrabalhoPendente.getRota());
            logTrabalhoPendenteAtualizado.setSinalBrrisk(logTrabalhoPendente.getSinalBrrisk());
            logTrabalhoPendenteAtualizado.setSinalTcell(logTrabalhoPendente.getSinalTcell());
            logTrabalhoPendenteAtualizado.setSm(logTrabalhoPendente.getSm());
            logTrabalhoPendenteAtualizado.setTransportadoraId(logTrabalhoPendente.getTransportadoraId());
            logTrabalhoPendenteAtualizado.setUsuarioId(logTrabalhoPendente.getUsuarioId());
            // logTrabalhoPendenteAtualizado.setValorAntigo(valorAntigo);
            logTrabalhoPendenteAtualizado.setValorNovo(logTrabalhoPendente.getValorNovo());
            logTrabalhoPendenteAtualizado.setVinculo(logTrabalhoPendente.getVinculo());

            //Salva o registro atualizado            
            return logTrabalhoPendenteRepository.save(logTrabalhoPendenteAtualizado);     

        }else{
            //Caso o registro não exista, retorna como nulo.
            return null;            
        }
   
    }

    public List<Log_Trabalho_PendenteEntity> listarTodos(){
        return logTrabalhoPendenteRepository.findAll();
    }

    public void excluir(Integer id){    
        logTrabalhoPendenteRepository.deleteById(id);
    }
}
