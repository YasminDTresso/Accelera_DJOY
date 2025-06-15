package com.djoy.accelera.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="checagemSensores")
@Data
public class ChecagemSensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transportadoraId", referencedColumnName = "id", nullable = false)
    private TransportadoraEntity transportadora;

    @ManyToOne
    @JoinColumn(name = "usuarioInclusaoId", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioInclusao;

    @ManyToOne
    @JoinColumn(name = "usuarioAlteracaoId", referencedColumnName = "id")
    private UsuarioEntity usuarioAlteracao;

    @Column(name="dataInclusao", nullable = false)
    private LocalDateTime dataInclusao;
    @PrePersist
    protected void onCreate() {
        this.dataInclusao = LocalDateTime.now();
    }

    @Column(name="dataAlteracao")
    private LocalDateTime dataAlteracao;

    @ManyToOne
    @JoinColumn(name = "gestorId", referencedColumnName = "id")
    private UsuarioEntity gestor;

    @ManyToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id")
    private VeiculoEntity veiculo;

    @Column(name="equipamento")
    private String equipamento;

    @Column(name="vinculo", nullable = true) 
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculo;

    @Column(name="status", nullable = true)
    @Enumerated(EnumType.STRING)
    private statusEtapa status;

    @Column(name="validade", nullable = true)
    private LocalDateTime validade;

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String problemaEquipamento;

    @Column(nullable = true)
    private Date inicioProblema;

    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String observacao;

}
