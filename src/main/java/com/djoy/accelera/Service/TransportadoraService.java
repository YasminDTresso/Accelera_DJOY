package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.PessoaJuridicaEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Repository.TransportadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class TransportadoraService {

    @SuppressWarnings("unused")

    private final TransportadoraRepository transportadoraRepository;

    /*====Incluir===*/
    public TransportadoraEntity incluir(TransportadoraEntity transportadora){
        return transportadoraRepository.save(transportadora);
    }

    /*====Editar===*/
    public TransportadoraEntity editar(PessoaJuridicaEntity id, TransportadoraEntity transportadora){
        //Verifica se o registro existe
        Optional<TransportadoraEntity> transportadoraExistente = transportadoraRepository.findById(id);

        if (transportadoraExistente.isPresent()) {
            //Atualiza o registro
            TransportadoraEntity transportadoraAtualizada = transportadoraExistente.get();

            // Atualiza os campos necessários
            transportadoraAtualizada.setTipoVinculo(transportadora.getTipoVinculo());

            // Salva o registro atualizado
            return transportadoraRepository.save(transportadoraAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<TransportadoraEntity> listarTodos(){
        return transportadoraRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(PessoaJuridicaEntity id){
        transportadoraRepository.deleteById(id);
    }   

}
