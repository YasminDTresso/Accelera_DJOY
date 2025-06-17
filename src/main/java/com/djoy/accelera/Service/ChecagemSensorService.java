package com.djoy.accelera.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.etapaChecagem;
import com.djoy.accelera.Entity.Enum.tipoVinculo;
import com.djoy.accelera.Repository.ChecagemSensorRepository;
import com.djoy.accelera.Service.Util.parseData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class ChecagemSensorService {

    @SuppressWarnings("unused")

    @PersistenceContext
    private EntityManager entityManager;

    private final ChecagemSensorRepository checagemSensorRepository;

    /*====Incluir===*/
    @Transactional
    public void incluir(UsuarioEntity usuarioInclusao, TransportadoraEntity transportadora, VeiculoEntity veiculo, UsuarioEntity gestor, String validade, String inicioProblema, 
                        String equipamento, String problemaEquipamento, String observacao, etapaChecagem status, tipoVinculo vinculo)
    {
        //Formatando as datas recebidas
        String validadeFormatada = parseData.formatarLocalDateTimeSQL(validade);
        String inicioProblemaFormatado = parseData.formatarLocalDateTimeSQL(inicioProblema);

        // System.out.println("Executando comando SQL:");
        // System.out.println(String.format(
        // "EXEC sp_criarChecagemSensor %d, %d, %d, %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s'",
        // usuarioInclusao.getId(),
        // transportadora.getId(),
        // veiculo.getId(),
        // gestor.getId(),
        // validadeFormatada,
        // inicioProblemaFormatado,
        // equipamento,
        // problemaEquipamento,
        // observacao,
        // status.toString(),
        // vinculo.toString()
        // ));

        Query query = entityManager.createNativeQuery("EXEC sp_criarChecagemSensor ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?")
            .setParameter(1, usuarioInclusao.getId())
            .setParameter(2, transportadora.getId())
            .setParameter(3, veiculo.getId())
            .setParameter(4, gestor.getId())
            .setParameter(5, validadeFormatada)
            .setParameter(6, inicioProblemaFormatado)
            .setParameter(7, equipamento)
            .setParameter(8, problemaEquipamento)
            .setParameter(9, observacao)
            .setParameter(10, status.toString())
            .setParameter(11, vinculo.toString())
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
    public ChecagemSensorEntity editar(ChecagemSensorEntity checagemSensor, UsuarioEntity usuarioAlteracao, VeiculoEntity veiculo, UsuarioEntity gestor, String validade,
                                        String problemaEquipamento, String observacao, etapaChecagem status, tipoVinculo vinculo){

        //Verifica se o registro existe
        Optional<ChecagemSensorEntity> checagemSensorExistente = checagemSensorRepository.findById(checagemSensor.getId());  

        if(checagemSensorExistente.isPresent()){

            //Formatando as datas recebidas
            String validadeFormatada = parseData.formatarLocalDateTimeSQL(validade);

            System.out.println("Executando comando SQL:");
            System.out.println(String.format(
            "EXEC sp_editarChecagemSensor %d, %d, %d, %d, '%s', '%s', '%s', '%s', '%s'",
            checagemSensor.getId(),
            usuarioAlteracao.getId(),
            veiculo.getId(),
            gestor.getId(),
            validadeFormatada,
            problemaEquipamento,
            observacao,
            status.toString(),
            vinculo.toString()
            ));

            Query query = entityManager.createNativeQuery("EXEC sp_editarChecagemSensor ?, ?, ?, ?, ?, ?, ?, ?, ?")
            .setParameter(1, checagemSensor.getId())
            .setParameter(2, usuarioAlteracao.getId())
            .setParameter(3, veiculo.getId())
            .setParameter(4, gestor.getId())
            .setParameter(5, validadeFormatada)
            .setParameter(6, problemaEquipamento)
            .setParameter(7, observacao)
            .setParameter(8, status.toString())
            .setParameter(9, vinculo.toString());

            try {
                query.executeUpdate();
                entityManager.flush();
                entityManager.refresh(checagemSensor);
                // entityManager.clear();
            } catch (Exception e) {
                e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
            }            

            //Retornando objeto atualizado
            ChecagemSensorEntity checagemSensorAtualizada = checagemSensorRepository.findById(checagemSensor.getId())
            .orElseThrow(() -> new RuntimeException("ChecagemSensor não encontrada após atualização"));
            return checagemSensorAtualizada;

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }   

    /*====Listar Todos===*/
    public List<ChecagemSensorEntity> listarTodos(){
    return checagemSensorRepository.findAll();
    }

    /*====Deletar===*/
    public ChecagemSensorEntity excluir(Integer id, UsuarioEntity usuarioAlteracao){

    //Verifica se o registro existe
    Optional<ChecagemSensorEntity> checagemSensorExistente = checagemSensorRepository.findById(id);

    if(checagemSensorExistente.isPresent()){

        //Atualiza o registro
        ChecagemSensorEntity checagemSensorAtualizada = checagemSensorExistente.get();

        //Atualiza os campos necessários
        checagemSensorAtualizada.setStatus(etapaChecagem.CANCELADO);
        checagemSensorAtualizada.setUsuarioAlteracao(usuarioAlteracao);

        //Salva a alteração
        return checagemSensorRepository.save(checagemSensorAtualizada);

    }else{
        // Caso o registro não exista, retorna como nulo.
        return null;
    }

    } 

}
