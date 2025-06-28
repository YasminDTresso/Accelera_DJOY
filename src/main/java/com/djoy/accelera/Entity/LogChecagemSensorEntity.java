package com.djoy.accelera.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.djoy.accelera.Entity.Enum.etapaChecagem;
import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;
import com.djoy.accelera.Entity.Key.LogChecagemSensorKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="logChecagemSensores")
@Data
public class LogChecagemSensorEntity {

    @EmbeddedId
    private LogChecagemSensorKey id;

    @ManyToOne
    @JoinColumn(name = "transportadoraId", referencedColumnName = "id", nullable = false)
    private TransportadoraEntity transportadora;

    @ManyToOne
    @JoinColumn(name = "usuarioAlteracaoId", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioAlteracao;

    @ManyToOne
    @JoinColumn(name = "gestorId", referencedColumnName = "id")
    private UsuarioEntity gestor;

    @ManyToOne
    @JoinColumn(name = "veiculoAnteriorId", referencedColumnName = "id", nullable = false)
    private VeiculoEntity veiculoAnterior;

    @ManyToOne
    @JoinColumn(name = "veiculoNovoId", referencedColumnName = "id")
    private VeiculoEntity veiculoNovo;    

    // @Column(name="dataAlteracao", nullable = false)
    // private LocalDateTime dataAlteracao;
    // @PrePersist
    // protected void onCreate() {
    //     this.dataAlteracao = LocalDateTime.now();
    // }

    @Column
    private String equipamentoAnterior;

    @Column
    private String equipamentoNovo;

    @Column
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculoAnterior;

    @Column
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculoNovo;

    @Column
    @Enumerated(EnumType.STRING)
    private etapaChecagem statusAnterior;

    @Column
    @Enumerated(EnumType.STRING)
    private etapaChecagem statusNovo;

    @Column
    private LocalDateTime validadeAnterior;

    @Column
    private LocalDateTime validadeNova;    

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String problemaEquipamentoAnterior;

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String problemaEquipamentoNovo;

    @Column(nullable = true)
    private Date inicioProblemaAnterior;

    @Column(nullable = true)
    private Date inicioProblemaNovo;    

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String observacaoAnterior;    

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String observacaoNova;    

}
