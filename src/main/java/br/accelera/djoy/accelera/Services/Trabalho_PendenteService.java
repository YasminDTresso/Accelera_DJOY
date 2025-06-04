package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Entities.Trabalho_PendenteEntity;
import br.accelera.djoy.accelera.Repositories.Trabalho_PendenteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class Trabalho_PendenteService {

    @SuppressWarnings("unused")
    @Autowired
    private final Trabalho_PendenteRepository trabalhoPendenteRepository;

    public Trabalho_PendenteEntity incluir(Trabalho_PendenteEntity trabalhoPendente){
        return trabalhoPendenteRepository.save(trabalhoPendente);
    }

    public Trabalho_PendenteEntity editar(int id, Trabalho_PendenteEntity trabalhoPendente){

        //Verifica se o registro existe
        Optional<Trabalho_PendenteEntity> trabalhoPendenteExistente = trabalhoPendenteRepository.findById(id);

        if(trabalhoPendenteExistente.isPresent()){
            // Atualiza o registro
            Trabalho_PendenteEntity trabalhoPendenteAtualizado = trabalhoPendenteExistente.get();

            // Atualiza os campos necessários
            trabalhoPendenteAtualizado.setCheckList(trabalhoPendente.getCheckList());
            trabalhoPendenteAtualizado.setCondutor(trabalhoPendente.getCondutor());
            trabalhoPendenteAtualizado.setConsulta(trabalhoPendente.getConsulta());
            // trabalhoPendenteAtualizado.setDataInclusao(dataInclusao);
            trabalhoPendenteAtualizado.setObservacao(trabalhoPendente.getObservacao());
            trabalhoPendenteAtualizado.setPlacaCarreta(trabalhoPendente.getPlacaCarreta());
            trabalhoPendenteAtualizado.setPlacaVeiculo(trabalhoPendente.getPlacaVeiculo());
            trabalhoPendenteAtualizado.setRota(trabalhoPendente.getRota());
            trabalhoPendenteAtualizado.setSinalBrrisk(trabalhoPendente.getSinalBrrisk());
            trabalhoPendenteAtualizado.setSinalTcell(trabalhoPendente.getSinalTcell());
            trabalhoPendenteAtualizado.setSm(trabalhoPendente.getSm());
            trabalhoPendenteAtualizado.setTransportadoraId(trabalhoPendente.getTransportadoraId());
            trabalhoPendenteAtualizado.setUsuarioId(trabalhoPendente.getUsuarioId());
            trabalhoPendenteAtualizado.setVinculo(trabalhoPendente.getVinculo());

            // Salva o registro atualizado
            return trabalhoPendenteRepository.save(trabalhoPendenteAtualizado);
        }else{
            //Caso o registro não exista, retorna como nulo.
            return null;                 
        }
        
    }    

    public List<Trabalho_PendenteEntity> listarTodos(){
        return trabalhoPendenteRepository.findAll();
    }    

    public void excluir(Integer id){
        trabalhoPendenteRepository.deleteById(id);
    }
}
