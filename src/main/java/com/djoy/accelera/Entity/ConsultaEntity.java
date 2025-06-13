package com.djoy.accelera.Entity;

import java.sql.Date;
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
@Table(name="consultas")
@Data
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @Column(name="dataAlteracao", nullable = true)
    private LocalDateTime dataAlteracao;

    @ManyToOne
    @JoinColumn(name = "transportadoraId", referencedColumnName = "id")
    private TransportadoraEntity transportadora;

    @ManyToOne
    @JoinColumn(name = "condutorId", referencedColumnName = "id")    
    private CondutorEntity condutor;

    @ManyToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id")   
    private VeiculoEntity veiculo;

    @Column(name="vinculo", nullable = true) 
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculo;

    @Column(name="status", nullable = true)
    @Enumerated(EnumType.STRING)
    private statusEtapa status;

    @Column(name="validade", nullable = true)
    private Date validade;

    @Column(name="observacao", columnDefinition = "TEXT", nullable = true)
    private String observacao;

}
