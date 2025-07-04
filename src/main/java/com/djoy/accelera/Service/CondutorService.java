package com.djoy.accelera.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Repository.CondutorRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class CondutorService {

    @SuppressWarnings("unused")

    private final CondutorRepository condutorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /*====Incluir===*/
    @Transactional
    public CondutorEntity incluir(
        String cpf,
        String cnh,
        Date dataNascimento,
        String telefone,
        String nome,
        String email
    )
    {
        System.out.println("Executando comando SQL:");
        System.out.println(String.format(
        "EXEC sp_criarCondutor %s, %s, '%s', %s, '%s', '%s''",
                cpf, cnh, dataNascimento, telefone, nome, email
        ));

        Query query = entityManager.createNativeQuery("EXEC sp_criarCondutor ?, ?, ?, ?, ?, ?")
        .setParameter(1, cpf)
        .setParameter(2, cnh)
        .setParameter(3, dataNascimento)
        .setParameter(4, telefone)  
        .setParameter(5, nome)
        .setParameter(6, email)            
        ;

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }

        return condutorRepository.findByCnh(cnh.trim())
         .orElseThrow(() -> new RuntimeException("Condutor não encontrado após criação."));
    }

    /*====Editar===*/
    /*--Para editar o condutor, utilizaremos o método editar Pessoa, pois a cnh não pode ser alterada pelo usuario */

    /*====Listar Todos===*/
    public List<CondutorEntity> listarTodos(){
        return condutorRepository.findAll();
    }

    /*===Deletar===*/
    public CondutorEntity excluir(Integer id, CondutorEntity condutor){


        //Verifica se o registro existe
        Optional<CondutorEntity> condutorExistente = condutorRepository.findById(id);

        if(condutorExistente.isPresent()){

            //Atualiza o registro
            CondutorEntity condutorAtualizado = condutorExistente.get();

            //Atualiza os campos necessários
            condutorAtualizado.setRegistroAtivo(false);

            return condutorRepository.save(condutorAtualizado);

        }else{
            return null;
        }
    }

}
