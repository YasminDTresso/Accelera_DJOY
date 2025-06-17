package com.djoy.accelera.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer>{

	//Para chamadas diretas, porém no service utilizei a chamada por query
    //====================Procedure criar consulta====================
    @Procedure(name = "sp_criarConsulta")
    void criarConsulta(
		@Param("v_idUsuarioInclusao") UsuarioEntity usuarioInclusao,
		@Param("v_idTransportadora") TransportadoraEntity transportadora,
		@Param("v_idCondutor") CondutorEntity condutor,
		@Param("v_idVeiculo") VeiculoEntity veiculo,
		@Param("v_validade") LocalDateTime validade,
		@Param("v_observacao") String observacao, 
		@Param("v_status") statusEtapa status,
		@Param("v_vinculo") tipoVinculo vinculo
    );

    //====================Procedure editar consulta====================	
    @Procedure(name = "sp_editarConsulta")
	void editarConsulta(
	@Param("v_idConsulta") ConsultaEntity consulta,
	@Param("v_idCondutor") CondutorEntity condutor,
	@Param("v_idUsuarioAlteracao") UsuarioEntity usuarioAlteracao,
	@Param("v_idVeiculo") VeiculoEntity veiculo,
	@Param("v_validade") LocalDateTime validade,
	@Param("v_observacao") String observacao,
	@Param("v_status") statusEtapa status,
	@Param("v_vinculo") tipoVinculo vinculo
	);

	//====================Método para filtrar consulta====================	
	@Query("SELECT c FROM ConsultaEntity c WHERE c.status <> :status")
	List<ConsultaEntity> findAllExcludingCertainStatus(@Param("status") statusEtapa status);
}
