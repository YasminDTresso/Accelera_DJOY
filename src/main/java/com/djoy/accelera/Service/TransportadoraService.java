package com.djoy.accelera.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Repository.CondutorRepository;
import com.djoy.accelera.Repository.TransportadoraRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class TransportadoraService {

    @SuppressWarnings("unused")

    private final TransportadoraRepository transportadoraRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /*====Incluir===*/
    @Transactional
    public TransportadoraEntity incluir(
        String cnpj,
        Date dataNascimento,
        String telefone,
        String nome,
        String nomeFantasia,
        String email, 
        String tipoServico, 
        String tipoVinculo
    )
    {
        System.out.println("Executando comando SQL:");
        System.out.println(String.format(
        "EXEC sp_criarTransportadora %s, %s, '%s', %s, '%s', '%s', '%s', '%s''",
                cnpj, dataNascimento, telefone, nome, nomeFantasia, email, tipoServico, tipoVinculo
        ));

        Query query = entityManager.createNativeQuery("EXEC sp_criarTransportadora ?, ?, ?, ?, ?, ?,?,?")
        .setParameter(1, cnpj)
        .setParameter(2, dataNascimento)
        .setParameter(3, telefone)
        .setParameter(4, nome)  
        .setParameter(5, nomeFantasia)
        .setParameter(6, email) 
        .setParameter(7, tipoServico)     
        .setParameter(8, tipoVinculo)                
        ;

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }

        return transportadoraRepository.findByCnpj(cnpj.trim())
         .orElseThrow(() -> new RuntimeException("Transportadora não encontrado após criação."));
    }

    /*====Editar===*/
    @Transactional
    public TransportadoraEntity editar(
        String cnpj,
        Date dataNascimento,
        String telefone,
        String nome,
        String nomeFantasia,
        String email, 
        String tipoServico, 
        String tipoVinculo
    )
    {
        System.out.println("Executando comando SQL:");
        System.out.println(String.format(
        "EXEC sp_editarTransportadora %s, %s, '%s', %s, '%s', '%s', '%s', '%s''",
                cnpj, dataNascimento, telefone, nome, nomeFantasia, email, tipoServico, tipoVinculo
        ));

        Query query = entityManager.createNativeQuery("EXEC sp_editarTransportadora ?, ?, ?, ?, ?, ?,?,?")
        .setParameter(1, cnpj)
        .setParameter(2, dataNascimento)
        .setParameter(3, telefone)
        .setParameter(4, nome)  
        .setParameter(5, nomeFantasia)
        .setParameter(6, email) 
        .setParameter(7, tipoServico)     
        .setParameter(8, tipoVinculo)                
        ;

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }

        return transportadoraRepository.findByCnpj(cnpj.trim())
         .orElseThrow(() -> new RuntimeException("Transportadora não encontrado após criação."));
    }

    /*====Listar Todos===*/
    public List<TransportadoraEntity> listarTodos(){
        return transportadoraRepository.findAll();
    }

    /*===Deletar===*/
    public TransportadoraEntity excluir(Integer id, TransportadoraEntity Transportadora){


        //Verifica se o registro existe
        Optional<TransportadoraEntity> transportadoraExistente = transportadoraRepository.findById(id);

        if(transportadoraExistente.isPresent()){

            //Atualiza o registro
            TransportadoraEntity transportadoraAtualizada = transportadoraExistente.get();

            //Atualiza os campos necessários
            transportadoraAtualizada.setRegistroAtivo(false);

            return transportadoraRepository.save(transportadoraAtualizada);

        }else{
            return null;
        }
    }

}
