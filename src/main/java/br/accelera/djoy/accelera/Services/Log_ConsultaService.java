package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Entities.Log_ConsultaEntity;
import br.accelera.djoy.accelera.Repositories.Log_ConsultaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class Log_ConsultaService {

    @SuppressWarnings("unused")
    @Autowired
    private final Log_ConsultaRepository logConsultaRepository;

    public Log_ConsultaEntity incluir(Log_ConsultaEntity logConsulta){
        return logConsultaRepository.save(logConsulta);
    }

    public Log_ConsultaEntity editar(int id, Log_ConsultaEntity logConsulta){

        //Verifica se o registro existe
        Optional<Log_ConsultaEntity> logConsultaExistente = logConsultaRepository.findById(id);

        if(logConsultaExistente.isPresent()){
            //Atualiza o registro
            Log_ConsultaEntity logConsultaAtualizada = logConsultaExistente.get();

            //Atualiza os campos necessários
            logConsultaAtualizada.setCheckList(logConsulta.getCheckList());
            logConsultaAtualizada.setCondutor(logConsulta.getCondutor());
            logConsultaAtualizada.setConsulta(logConsulta.getConsulta());
            logConsultaAtualizada.setDataFinalizacao(logConsulta.getDataFinalizacao());
            // logConsultaAtualizada.setDataInclusao();
            logConsultaAtualizada.setObservacao(logConsulta.getObservacao());
            logConsultaAtualizada.setPlacaCarreta(logConsulta.getPlacaCarreta());;
            logConsultaAtualizada.setPlacaVeiculo(logConsulta.getPlacaVeiculo());
            logConsultaAtualizada.setRota(logConsulta.getRota());
            logConsultaAtualizada.setSinalBrrisk(logConsulta.getSinalBrrisk());
            logConsultaAtualizada.setSinalTcell(logConsulta.getSinalTcell());
            logConsultaAtualizada.setSm(logConsulta.getSm());
            logConsultaAtualizada.setTransportadoraId(logConsulta.getTransportadoraId());
            logConsultaAtualizada.setUsuarioId(logConsulta.getUsuarioId());
            logConsultaAtualizada.setVinculo(logConsulta.getVinculo());

            //Salva o registro atualizado
           return logConsultaRepository.save(logConsultaAtualizada);   

        }else{
            //Caso o registro não exista, retorna como nulo.
            return null;
        }

  
    }

    public List<Log_ConsultaEntity> listarTodos(){
        return logConsultaRepository.findAll();         
    }

    public void excluir(Integer id){
        logConsultaRepository.deleteById(id);
    }

}
