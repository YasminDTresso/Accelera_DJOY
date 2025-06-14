package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class PessoaService {

    @SuppressWarnings("unused")

    private final PessoaRepository pessoaRepository;

     /*====Incluir===*/
    public PessoaEntity incluir(PessoaEntity pessoa){
        return pessoaRepository.save(pessoa);
    }

    /*====Editar===*/
    public PessoaEntity editar(int id, PessoaEntity pessoa){
        //Verifica se o registro existe
        Optional<PessoaEntity> pessoaExistente = pessoaRepository.findById(id);

        if (pessoaExistente.isPresent()) {
            //Atualiza o registro
            PessoaEntity pessoaAtualizada = pessoaExistente.get();

            // Atualiza os campos necessários
            pessoaAtualizada.setDataNascimento(pessoa.getDataNascimento());
            pessoaAtualizada.setEmail(pessoa.getEmail());
            pessoaAtualizada.setNome(pessoa.getNome());
            pessoaAtualizada.setTelefone(pessoa.getTelefone());;           

            // Salva o registro atualizado
            return pessoaRepository.save(pessoaAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<PessoaEntity> listarTodos(){
        return pessoaRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        pessoaRepository.deleteById(id);
    } 

}
