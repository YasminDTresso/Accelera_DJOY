package com.djoy.accelera.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

@Repository
public interface  AgendaRepository extends JpaRepository<AgendaEntity, Integer>{

    //Para chamadas diretas, porém no service utilizei a chamada por query
    //====================Procedure criar agenda====================
    @Procedure(name = "sp_criarAgenda")
    void criarAgenda(
        @Param("v_idUsuarioInclusao") UsuarioEntity usuarioInclusao,
        @Param("v_idTransportadora") TransportadoraEntity transportadora,
        @Param("v_idCondutor") PessoaFisicaEntity condutor,
        @Param("v_idVeiculo") VeiculoEntity veiculo, 
        @Param("v_idConsulta") ConsultaEntity consulta,
        @Param("v_idChecagemSensor") ChecagemSensorEntity checagemSensor,
        @Param("v_observacao") String observacao,
        @Param("v_rota") statusRota rota,
        @Param("v_sinalBRRISK") statusSinalBRRISK sinalBRRISK,
        @Param("v_sinalTCELL") statusSinalTCELL sinalTCELL,
        @Param("v_SM") statusSM SM
    );

    //====================Procedure editar agenda====================	
    @Procedure(name = "sp_editarAgenda")
    void editarAgenda(
        @Param("v_idAgenda") AgendaEntity agenda,
        @Param("v_idUsuarioAlteracao") UsuarioEntity usuarioAlteracao,
        @Param("v_idCondutor") CondutorEntity condutor,
        @Param("v_idVeiculo") VeiculoEntity veiculo, 
        @Param("v_idConsulta") ConsultaEntity consulta,
        @Param("v_idChecagemSensor") ChecagemSensorEntity checagemSensor,
        @Param("v_observacao") String observacao,
        @Param("v_rota")  statusRota rota,
        @Param("v_sinalBRRISK") statusSinalBRRISK sinalBRRISK,
        @Param("v_sinalTCELL") statusSinalTCELL sinalTCELL,
        @Param("v_SM") statusSM SM
    );

    //====================Método para filtrar agenda====================	
	@Query("SELECT a FROM AgendaEntity a WHERE a.rota <> :rota")
	List<AgendaEntity> findAllExcludingCertainStatus(@Param("rota") statusRota rota);

}
