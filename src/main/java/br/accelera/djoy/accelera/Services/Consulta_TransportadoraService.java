package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Entities.Consulta_TransportadoraEntitiy;
import br.accelera.djoy.accelera.Repositories.Consulta_TransportadoraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class Consulta_TransportadoraService {

    @SuppressWarnings("unused")
    @Autowired
    private final Consulta_TransportadoraRepository consultaTransportadoraRepository;

    public Consulta_TransportadoraEntitiy incluir(Consulta_TransportadoraEntitiy consultaTransportadora){
        return consultaTransportadoraRepository.save(consultaTransportadora);
    }
    
    public Consulta_TransportadoraEntitiy editar(int id, Consulta_TransportadoraEntitiy consultaTransportadora){
        //Verifica se o registro existe
        Optional<Consulta_TransportadoraEntitiy> consultaTransportadoraExistente = consultaTransportadoraRepository.findById(id);

        if (consultaTransportadoraExistente.isPresent()) {
            //Atualiza o registro
            Consulta_TransportadoraEntitiy consultaTransportadoraAtualizada = consultaTransportadoraExistente.get();

            // Atualiza os campos necessários
            consultaTransportadoraAtualizada.setObjetoConsulta(consultaTransportadora.getObjetoConsulta()); 
            consultaTransportadoraAtualizada.setObservacao(consultaTransportadora.getObservacao());
            consultaTransportadoraAtualizada.setStatus(consultaTransportadora.getStatus());
            consultaTransportadoraAtualizada.setTransportadoraId(consultaTransportadora.getTransportadoraId());
            consultaTransportadoraAtualizada.setUsuarioId(consultaTransportadora.getUsuarioId());
            consultaTransportadoraAtualizada.setValidade(consultaTransportadora.getValidade());
            consultaTransportadoraAtualizada.setVinculo(consultaTransportadora.getVinculo());

            // Salva o registro atualizado
            return consultaTransportadoraRepository.save(consultaTransportadoraAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    public List<Consulta_TransportadoraEntitiy> listarTodos(){
        return consultaTransportadoraRepository.findAll();
    }

    public void excluir(Integer id){
        consultaTransportadoraRepository.deleteById(id);
    }
}
