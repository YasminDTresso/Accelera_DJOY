package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.PessoaJuridicaEntity;
import com.djoy.accelera.Repository.PessoaJuridicaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class PessoaJuridicaService {

    @SuppressWarnings("unused")

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

     /*====Incluir===*/
    public PessoaJuridicaEntity incluir(PessoaJuridicaEntity pessoaJuridica){
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    /*====Editar===*/
    public PessoaJuridicaEntity editar(PessoaEntity id, PessoaJuridicaEntity pessoaJuridica){
        //Verifica se o registro existe
        Optional<PessoaJuridicaEntity> pessoaJuridicaExistente = pessoaJuridicaRepository.findById(id);

        if (pessoaJuridicaExistente.isPresent()) {
            //Atualiza o registro
            PessoaJuridicaEntity pessoaJuridicaAtualizada = pessoaJuridicaExistente.get();

            // Atualiza os campos necessários
            pessoaJuridicaAtualizada.setCnpj(pessoaJuridica.getCnpj());
            pessoaJuridicaAtualizada.setNomeFantasia(pessoaJuridica.getNomeFantasia());

            // Salva o registro atualizado
            return pessoaJuridicaRepository.save(pessoaJuridicaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<PessoaJuridicaEntity> listarTodos(){
        return pessoaJuridicaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(PessoaEntity id){
        pessoaJuridicaRepository.deleteById(id);
    }    

}
