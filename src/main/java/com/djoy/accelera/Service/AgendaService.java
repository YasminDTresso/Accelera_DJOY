package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.AgendaEntity;
import com.djoy.accelera.Repository.AgendaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class AgendaService {

    @SuppressWarnings("unused")

    private final AgendaRepository agendaRepository;

    /*====Incluir===*/
    public AgendaEntity incluir(AgendaEntity agenda){
        return agendaRepository.save(agenda);
    }

    /*====Editar===*/
    public AgendaEntity editar(int id, AgendaEntity agenda){
        //Verifica se o registro existe
        Optional<AgendaEntity> agendaExistente = agendaRepository.findById(id);

        if (agendaExistente.isPresent()) {
            //Atualiza o registro
            AgendaEntity agendaAtualizada = agendaExistente.get();

            // Atualiza os campos necessários
            agendaAtualizada.setChecagemSensor(agenda.getChecagemSensor());
            agendaAtualizada.setCondutor(agenda.getCondutor());
            agendaAtualizada.setConsulta(agenda.getConsulta());
            agendaAtualizada.setDataAlteracao(agenda.getDataAlteracao());
            agendaAtualizada.setObservacao(agenda.getObservacao());
            agendaAtualizada.setRota(agenda.getRota());
            agendaAtualizada.setSinalBrrisk(agenda.getSinalBrrisk());
            agendaAtualizada.setSinalTcell(agenda.getSinalTcell());
            agendaAtualizada.setSm(agenda.getSm());
            agendaAtualizada.setTransportadora(agenda.getTransportadora());
            agendaAtualizada.setUsuarioAlteracao(agenda.getUsuarioAlteracao());
            agendaAtualizada.setVeiculo(agenda.getVeiculo());

            // Salva o registro atualizado
            return agendaRepository.save(agendaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<AgendaEntity> listarTodos(){
        return agendaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        agendaRepository.deleteById(id);
    }

}
