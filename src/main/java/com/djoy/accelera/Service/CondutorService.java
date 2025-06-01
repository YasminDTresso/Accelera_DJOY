package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Repository.CondutorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class CondutorService {

    @SuppressWarnings("unused")

    private final CondutorRepository condutorRepository;

    /*====Incluir===*/
    public CondutorEntity incluir(CondutorEntity condutor){
        return condutorRepository.save(condutor);
    }

    /*====Editar===*/
    public CondutorEntity editar(PessoaFisicaEntity id, CondutorEntity condutor){
        //Verifica se o registro existe
        Optional<CondutorEntity> condutorExistente = condutorRepository.findById(id);

        if (condutorExistente.isPresent()) {
            //Atualiza o registro
            CondutorEntity condutorAtualizado = condutorExistente.get();

            // Atualiza os campos necessários
            condutorAtualizado.setCnh(condutor.getCnh());

            // Salva o registro atualizado
            return condutorRepository.save(condutorAtualizado);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<CondutorEntity> listarTodos(){
        return condutorRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(PessoaFisicaEntity id){
        condutorRepository.deleteById(id);
    }

}
