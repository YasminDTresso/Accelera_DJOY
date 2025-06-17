package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Repository.ConsultaRepository;
import com.djoy.accelera.Service.Util.parseData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class ConsultaService {

    @SuppressWarnings("unused")

    @PersistenceContext
    private EntityManager entityManager;

    private final ConsultaRepository consultaRepository;

    /*====Incluir===*/
    @Transactional
    public void incluir(UsuarioEntity usuarioInclusao, TransportadoraEntity transportadora, 
                        CondutorEntity condutor, VeiculoEntity veiculo, String validade, 
                        String observacao, statusEtapa status, tipoVinculo vinculo)
    {
        //Formatando as datas recebidas
        String validadeFormatada = parseData.formatarLocalDateTimeSQL(validade);

        Query query = entityManager.createNativeQuery("EXEC sp_criarConsulta ?, ?, ?, ?, ?, ?, ?, ?")
              .setParameter(1, usuarioInclusao.getId())
              .setParameter(2, transportadora.getId())
              .setParameter(3, condutor.getId())
              .setParameter(4, veiculo.getId())  
              .setParameter(5, validadeFormatada)
              .setParameter(6, observacao)
              .setParameter(7, status.toString())
              .setParameter(8, vinculo.toString())                
              ;

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }

        // System.out.println("Executando a procedure com:" + "usuarioInclusao" + usuarioInclusao.getId()+ "transportadora" + transportadora.getId()+ "condutor" + condutor.getId()+ "veiculo" + veiculo.getId()+ "validade" +validadeFormatada+ "observacao" +observacao+ "status" + status+ "vinculo" + vinculo);
     }
  
    /*====Editar===*/
    @Transactional
    public ConsultaEntity editar(ConsultaEntity consulta, CondutorEntity condutor, UsuarioEntity usuarioAlteracao, VeiculoEntity veiculo, String validade, String observacao, statusEtapa status, tipoVinculo vinculo){

        //Verifica se o registro existe
        Optional<ConsultaEntity> consultaExistente = consultaRepository.findById(consulta.getId());

        if(consultaExistente.isPresent()){

            String validadeFormatada = parseData.formatarLocalDateTimeSQL(validade);

            // String sqlProcedure = String.format(
            //     "EXEC sp_editarConsulta %d, %d, %d, %d, '%s', '%s', '%s', '%s'",
            //     consulta.getId(),
            //     condutor.getId(),
            //     usuarioAlteracao.getId(),
            //     veiculo.getId(),
            //     validadeFormatada,
            //     observacao,
            //     status.toString(),
            //     vinculo.toString()
            // );

            // System.out.println("Executando SQL Procedure:");
            // System.out.println(sqlProcedure);        

            Query query = entityManager.createNativeQuery("EXEC sp_editarConsulta ?, ?, ?, ?, ?, ?, ?, ?")
                .setParameter(1, consulta.getId())
                .setParameter(2, condutor.getId())
                .setParameter(3, usuarioAlteracao.getId())
                .setParameter(4, veiculo.getId())  
                .setParameter(5, validadeFormatada)
                .setParameter(6, observacao)
                .setParameter(7, status.toString())
                .setParameter(8, vinculo.toString())                
                ;

            try {
                query.executeUpdate();
                entityManager.flush();
                entityManager.refresh(consulta);
                // entityManager.clear();
            } catch (Exception e) {
                e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
            }            

            //Retornando objeto atualizado
            ConsultaEntity consultaAtualizada = consultaRepository.findById(consulta.getId())
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada após atualização"));

            return consultaAtualizada;

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }     

    /*====Listar Todos===*/
    public List<ConsultaEntity> listarTodos(){
        return consultaRepository.findAll();
    }

    /*====Deletar===*/
    public ConsultaEntity excluir(Integer id, UsuarioEntity usuarioAlteracao){
    
        //Verifica se o registro existe
        Optional<ConsultaEntity> consultaExistente = consultaRepository.findById(id);

        if(consultaExistente.isPresent()){

            //Atualiza o registro
            ConsultaEntity consultaAtualizada = consultaExistente.get();

            //Atualiza os campos necessários
            consultaAtualizada.setStatus(statusEtapa.CANCELADA);
            consultaAtualizada.setUsuarioAlteracao(usuarioAlteracao);

            //Salva a alteração
            return consultaRepository.save(consultaAtualizada);

        }else{
        // Caso o registro não exista, retorna como nulo.
        return null;
        }

    } 
}
