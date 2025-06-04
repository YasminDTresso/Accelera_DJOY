package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Repositories.TransportadoraRepository;
import br.accelera.djoy.accelera.Entities.TransportadoraEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class TransportadoraService {

    @SuppressWarnings("unused")
    @Autowired
    private final TransportadoraRepository transportadoraRepository;

    public TransportadoraEntity incluir(TransportadoraEntity transportadora){
        return transportadoraRepository.save(transportadora);
    }

    public TransportadoraEntity editar(int id, TransportadoraEntity transportadora){
        // Verifica se o registro existe
        Optional<TransportadoraEntity> transportadoraExistente = transportadoraRepository.findById(id);

        if (transportadoraExistente.isPresent()) {
            // Atualiza o registro
            TransportadoraEntity transportadoraAtualizada = transportadoraExistente.get();

            // Atualiza os campos necessários
            transportadoraAtualizada.setAgregado(transportadora.getAgregado());
            transportadoraAtualizada.setConsultaAgregado(transportadora.getConsultaAgregado());
            transportadoraAtualizada.setConsultaFixo(transportadora.getConsultaFixo());
            transportadoraAtualizada.setConsultaTerceiro(transportadora.getConsultaTerceiro());
            transportadoraAtualizada.setFixo(transportadora.getFixo());
            transportadoraAtualizada.setNome(transportadora.getNome());
            transportadoraAtualizada.setTerceiro(transportadora.getTerceiro());

            // Salva o registro atualizado
            return transportadoraRepository.save(transportadoraAtualizada);    
        }else{
            //Caso o registro não exista, retorna como nulo.
            return null;  
        }

    }

    public List<TransportadoraEntity> listarTodos(){
        return transportadoraRepository.findAll();
    }   

    public void excluir(Integer id){
        transportadoraRepository.deleteById(id);
    }
}
