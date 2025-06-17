package com.djoy.accelera.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Entity.Enum.etapaChecagem;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.tipoVinculo;

@Repository
public interface  ChecagemSensorRepository extends JpaRepository<ChecagemSensorEntity, Integer>{

    //====================Procedure criar checagem====================
        @Procedure(name = "sp_criarChecagemSensor")
        void criarChecagemSensor(
        @Param("v_idUsuarioInclusao") UsuarioEntity usuarioInclusao,
        @Param("v_idTransportadora") TransportadoraEntity transportadora,
        @Param("v_idVeiculo") VeiculoEntity veiculo,
        @Param("v_idGestor") UsuarioEntity gestor,
        @Param("v_validade") LocalDateTime validade,
        @Param("v_inicioProblema") LocalDateTime inicioProblema,
        @Param("v_equipamento") String equipamento,
        @Param("v_problemaEquipamento") String problemaEquipamento,
        @Param("v_observacao") String observacao,
        @Param("v_status") etapaChecagem status,
        @Param("v_vinculo") tipoVinculo vinculo
    );

    //====================Procedure editar checagem==================== 
         @Procedure(name = "sp_editarChecagemSensor")
        void editarChecagemSensor(
        @Param("v_idChecagemSensor") ChecagemSensorEntity checagemSensor,
        @Param("v_idUsuarioAlteracao") UsuarioEntity usuarioAlteracao,
        @Param("v_idVeiculo") VeiculoEntity veiculo,
        @Param("v_idGestor") UsuarioEntity gestor,
        @Param("v_validade") LocalDateTime validade,
        @Param("v_problemaEquipamento") String problemaEquipamento,
        @Param("v_observacao") String observacao,
        @Param("v_status") etapaChecagem status,
        @Param("v_vinculo") tipoVinculo vinculo
    );  
    //====================Método para filtrar checagem====================	
    @Query("SELECT c FROM ChecagemSensorEntity c WHERE c.status <> :status")
	List<ChecagemSensorEntity> findAllExcludingCertainStatus(@Param("status") etapaChecagem status);

}
