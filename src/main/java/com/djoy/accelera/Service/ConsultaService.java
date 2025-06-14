package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Repository.ConsultaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class ConsultaService {

    @SuppressWarnings("unused")

    private final ConsultaRepository consultaRepository;

    /*====Incluir===*/
    public ConsultaEntity incluir(ConsultaEntity consulta){
        return consultaRepository.save(consulta);
    }

    /*====Editar===*/
    public ConsultaEntity editar(int id, ConsultaEntity consulta){
        //Verifica se o registro existe
        Optional<ConsultaEntity> consultaExistente = consultaRepository.findById(id);

        if (consultaExistente.isPresent()) {
            //Atualiza o registro
            ConsultaEntity consultaAtualizada = consultaExistente.get();

            // Atualiza os campos necessários
            consultaAtualizada.setCondutor(consulta.getCondutor());
            consultaAtualizada.setDataAlteracao(LocalDateTime.now());
            consultaAtualizada.setObservacao(consulta.getObservacao());
            consultaAtualizada.setStatus(consulta.getStatus());
            consultaAtualizada.setTransportadora(consulta.getTransportadora());
            consultaAtualizada.setUsuarioAlteracao(consulta.getUsuarioAlteracao());
            consultaAtualizada.setValidade(consulta.getValidade());
            consultaAtualizada.setVeiculo(consulta.getVeiculo());
            consultaAtualizada.setVinculo(consulta.getVinculo());
            consultaAtualizada.setCondutor(consulta.getCondutor());

            // Salva o registro atualizado
            return consultaRepository.save(consultaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<ConsultaEntity> listarTodos(){
        return consultaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        consultaRepository.deleteById(id);
    }    

}
