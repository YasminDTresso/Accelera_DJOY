package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djoy.accelera.Entity.AgendaEntity;
import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.statusRota;
import com.djoy.accelera.Entity.Enum.statusSM;
import com.djoy.accelera.Entity.Enum.statusSinalBRRISK;
import com.djoy.accelera.Entity.Enum.statusSinalTCELL;
import com.djoy.accelera.Repository.AgendaRepository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class AgendaService {

    @SuppressWarnings("unused")

    @PersistenceContext
    private EntityManager entityManager;

    private final AgendaRepository agendaRepository;

    /*====Incluir===*/
    @Transactional
    public void incluir(UsuarioEntity usuarioInclusao, TransportadoraEntity transportadora, PessoaFisicaEntity condutor,
                        VeiculoEntity veiculo, ConsultaEntity consulta, ChecagemSensorEntity checagemSensor, String observacao,
                         statusRota rota, statusSinalBRRISK sinalBRRISK, statusSinalTCELL sinalTCELL, statusSM SM)
    {

        Query query = entityManager.createNativeQuery("EXEC sp_criarAgenda ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?")
                .setParameter(1, usuarioInclusao.getId())
                .setParameter(2, transportadora.getId())
                .setParameter(3, condutor.getId())
                .setParameter(4, veiculo.getId())  
                .setParameter(5, consulta.getId())
                .setParameter(6, checagemSensor.getId())
                .setParameter(7, observacao)
                .setParameter(8, rota.toString())  
                .setParameter(9, sinalBRRISK.toString()) 
                .setParameter(10, sinalTCELL.toString()) 
                .setParameter(11, SM.toString())               
                ;

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }

     }
  
    /*====Editar===*/
    @Transactional
    public AgendaEntity editar(AgendaEntity agenda, UsuarioEntity usuarioAlteracao, CondutorEntity condutor,VeiculoEntity veiculo, ConsultaEntity consulta,
                               ChecagemSensorEntity checagemSensor, String observacao, statusRota rota, statusSinalBRRISK sinalBRRISK, statusSinalTCELL sinalTCELL, statusSM SM){

        //Verifica se o registro existe
        Optional<AgendaEntity> agendaExistente = agendaRepository.findById(agenda.getId());

        if(agendaExistente.isPresent()){

            String sqlProcedure = String.format(
                "EXEC sp_editarAgenda %d, %d, %d, %d, %d,%d , '%s', '%s', '%s', '%s', '%s'",
                        agenda.getId(),
                        usuarioAlteracao.getId(),
                        condutor.getId(),
                        veiculo.getId(), 
                        consulta.getId(),
                        checagemSensor.getId(),
                        observacao,
                        rota.toString(),  
                        sinalBRRISK.toString(), 
                        sinalTCELL.toString(), 
                        SM.toString()  
                 );

            System.out.println("Executando SQL Procedure:");
            System.out.println(sqlProcedure);        

            Query query = entityManager.createNativeQuery("EXEC sp_editarAgenda ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?")
                    .setParameter(1, agenda.getId())
                    .setParameter(2, usuarioAlteracao.getId())
                    .setParameter(3, condutor.getId())
                    .setParameter(4, veiculo.getId())  
                    .setParameter(5, consulta.getId())
                    .setParameter(6, checagemSensor.getId())
                    .setParameter(7, observacao)
                    .setParameter(8, rota.toString())  
                    .setParameter(9, sinalBRRISK.toString()) 
                    .setParameter(10, sinalTCELL.toString()) 
                    .setParameter(11, SM.toString())               
                    ;

            try {
                query.executeUpdate();
                entityManager.flush();
                entityManager.refresh(agenda);
                // entityManager.clear();
            } catch (Exception e) {
                e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
            }            

            //Retornando objeto atualizado
            AgendaEntity agendaAtualizada = agendaRepository.findById(agenda.getId())
            .orElseThrow(() -> new RuntimeException("Agenda não encontrada após atualização"));

            return agendaAtualizada;

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }     

    /*====Listar Todos===*/
    public List<AgendaEntity> listarTodos(){
        return agendaRepository.findAll();
    }

    /*====Deletar===*/
    public AgendaEntity excluir(Integer id, UsuarioEntity usuarioAlteracao){
    
        //Verifica se o registro existe
        Optional<AgendaEntity> agendaExistente =agendaRepository.findById(id);

        if(agendaExistente.isPresent()){

            //Atualiza o registro
           AgendaEntity agendaAtualizada = agendaExistente.get();

            //Atualiza os campos necessários
           agendaAtualizada.setRota(statusRota.CANCELADA);
           agendaAtualizada.setUsuarioAlteracao(usuarioAlteracao);

            //Salva a alteração
            return agendaRepository.save(agendaAtualizada);

        }else{
        // Caso o registro não exista, retorna como nulo.
        return null;
        }
    } 

}
