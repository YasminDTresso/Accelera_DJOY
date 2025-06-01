package com.djoy.accelera.Entity;

import java.time.LocalDateTime;

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
@Table(name="logConsulta")
@Data
public class LogConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "consultaId", referencedColumnName = "id", nullable = false)
    private ConsultaEntity consulta;

    @ManyToOne
    @JoinColumn(name = "usuarioAlteracaoId", referencedColumnName = "pessoaFisicaId", nullable = false)
    private UsuarioEntity usuarioAlteracao;


    @Column(name="dataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;
    @PrePersist
    protected void onCreate() {
        this.dataAlteracao = LocalDateTime.now();
    }

    @Column(name="statusAnterior", nullable = true)
    @Enumerated(EnumType.STRING)
    private statusEtapa statusAnterior;

    @Column(name="statusNovo", nullable = false)
    @Enumerated(EnumType.STRING)
    private statusEtapa statusNovo;

    @Column(name="vinculoAnterior", nullable = true)
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculoAnterior;

    @Column(name="vinculoNovo", nullable = false)
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculoNovo;

    @Column(name="observacaoAnterior", columnDefinition = "TEXT", nullable = true)
    private String observacaoAnterior;

    @Column(name="observacaoNova", columnDefinition = "TEXT", nullable = true)
    private String observacaoNova;

}
