package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;
import com.djoy.accelera.Entity.Key.LogConsultaKey;

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
@Table(name="logConsultas")
@Data
public class LogConsultaEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private LogConsultaKey id;

    // @ManyToOne
    // @JoinColumn(name = "consultaId", referencedColumnName = "id", nullable = false)
    // private ConsultaEntity consulta;

    @ManyToOne
    @JoinColumn(name = "usuarioAlteracaoId", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioAlteracao;


    // @Column(name="dataAlteracao", nullable = false)
    // private LocalDateTime dataAlteracao;
    // @PrePersist
    // protected void onCreate() {
    //     this.dataAlteracao = LocalDateTime.now();
    // }

    @ManyToOne
    @JoinColumn(name = "transportadoraId", referencedColumnName = "id")
    private TransportadoraEntity transportadora;

    @ManyToOne
    @JoinColumn(name = "condutorId", referencedColumnName = "id")    
    private CondutorEntity condutor;

    @ManyToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id")   
    private VeiculoEntity veiculo;


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

    @Column(name="observacaoAnterior", columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String observacaoAnterior;

    @Column(name="observacaoNova", columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String observacaoNova;

}
