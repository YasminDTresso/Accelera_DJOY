package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.accelera.djoy.accelera.Entities.Controle_CheckListEntity;
import br.accelera.djoy.accelera.Repositories.Controle_CheckListRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class Controle_CheckListService {

    @SuppressWarnings("unused")
    @Autowired
    private final Controle_CheckListRepository controleCheckListRepository;

    public Controle_CheckListEntity incluir(Controle_CheckListEntity controleCheckListEntity){
        return controleCheckListRepository.save(controleCheckListEntity);
    }

    public Controle_CheckListEntity editar(int id, Controle_CheckListEntity controleCheckList){
        //Verifica se o registro existe
        Optional<Controle_CheckListEntity> controleCheckListExistente = controleCheckListRepository.findById(id);

        if(controleCheckListExistente.isPresent()){
            //Atualiza o registro
            Controle_CheckListEntity controleCheckListAtualizada = controleCheckListExistente.get();

            //Atualiza os campos necessários
            controleCheckListAtualizada.setEquipamento(controleCheckList.getEquipamento());
            controleCheckListAtualizada.setGestor(controleCheckList.getGestor());
            controleCheckListAtualizada.setInicioProblema(controleCheckList.getInicioProblema());
            controleCheckListAtualizada.setObservacao(controleCheckList.getObservacao());
            controleCheckListAtualizada.setPlaca(controleCheckList.getPlaca());
            controleCheckListAtualizada.setProblemaEquipamento(controleCheckList.getProblemaEquipamento());
            controleCheckListAtualizada.setProgramacao(controleCheckList.getProgramacao());
            controleCheckListAtualizada.setStatusCl(controleCheckList.getStatusCl());
            controleCheckListAtualizada.setTransportadoraId(controleCheckList.getTransportadoraId());
            controleCheckListAtualizada.setUsuarioId(controleCheckList.getUsuarioId());
            controleCheckListAtualizada.setValidade(controleCheckList.getValidade());
            controleCheckListAtualizada.setVinculo(controleCheckList.getVinculo());

            // Salva o registro atualizado
            return controleCheckListRepository.save(controleCheckListAtualizada);
        }
        else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }
        

    }

    public List<Controle_CheckListEntity> listarTodos(){
        return controleCheckListRepository.findAll();
    }

    public void excluir(Integer id){
        controleCheckListRepository.deleteById(id);
    }

}
