package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Repository.PessoaFisicaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class PessoaFisicaService {

    @SuppressWarnings("unused")

    private final PessoaFisicaRepository pessoaFisicaRepository;

     /*====Incluir===*/
    public PessoaFisicaEntity incluir(PessoaFisicaEntity pessoaFisica){
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    /*====Editar===*/
    public PessoaFisicaEntity editar(PessoaEntity id, PessoaFisicaEntity pessoaFisica){
        //Verifica se o registro existe
        Optional<PessoaFisicaEntity> pessoaFisicaExistente = pessoaFisicaRepository.findById(id);

        if (pessoaFisicaExistente.isPresent()) {
            //Atualiza o registro
            PessoaFisicaEntity pessoaFisicaAtualizada = pessoaFisicaExistente.get();

            // Atualiza os campos necessários
            pessoaFisicaAtualizada.setCpf(pessoaFisica.getCpf());

            // Salva o registro atualizado
            return pessoaFisicaRepository.save(pessoaFisicaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<PessoaFisicaEntity> listarTodos(){
        return pessoaFisicaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(PessoaEntity id){
        pessoaFisicaRepository.deleteById(id);
    }    


}
