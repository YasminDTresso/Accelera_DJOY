package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.SocioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.SocioTransportadoraKey;
import com.djoy.accelera.Repository.SocioTransportadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class SocioTransportadoraService {

    @SuppressWarnings("unused")

    private final SocioTransportadoraRepository socioTransportadoraRepository;

    /*====Incluir===*/
    public SocioTransportadoraEntity incluir(SocioTransportadoraEntity socioTransportadora){
        return socioTransportadoraRepository.save(socioTransportadora);
    }

    /*====Editar===*/
    public SocioTransportadoraEntity editar(SocioTransportadoraKey id, SocioTransportadoraEntity socioTransportadora){
        //Verifica se o registro existe
        Optional<SocioTransportadoraEntity> socioTransportadoraExistente = socioTransportadoraRepository.findById(id);

        if (socioTransportadoraExistente.isPresent()) {
            //Atualiza o registro
            SocioTransportadoraEntity socioTransportadoraAtualizada = socioTransportadoraExistente.get();

            // Atualiza os campos necessários
            socioTransportadoraAtualizada.setId(socioTransportadora.getId());
            // Salva o registro atualizado
            return socioTransportadoraRepository.save(socioTransportadoraAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<SocioTransportadoraEntity> listarTodos(){
        return socioTransportadoraRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(SocioTransportadoraKey id){
        socioTransportadoraRepository.deleteById(id);
    }    

}
