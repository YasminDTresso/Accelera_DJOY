package com.djoy.accelera.Entity;

import java.time.LocalDateTime;

import com.djoy.accelera.Entity.Enum.statusRota;
import com.djoy.accelera.Entity.Enum.statusSM;
import com.djoy.accelera.Entity.Enum.statusSinalBRRISK;
import com.djoy.accelera.Entity.Enum.statusSinalTCELL;

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
@Table(name="agendas")
@Data
public class AgendaEntity {

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

    @Column(name="dataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;
    @PrePersist
    protected void onCreate() {
        this.dataInclusao = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id")
    private VeiculoEntity veiculo;

    @ManyToOne 
    @JoinColumn(name = "consultaId", referencedColumnName = "id") 
    private ConsultaEntity consulta;

    @ManyToOne 
    @JoinColumn(name = "condutorId", referencedColumnName = "id") 
    private CondutorEntity condutor;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String observacao;

    @Column(name="sinalTcell", nullable = false)
    @Enumerated(EnumType.STRING)
    private statusSinalTCELL sinalTcell;
  
    @Enumerated(EnumType.STRING)
    @Column(name="sinalBrrisk", nullable = false)
    private statusSinalBRRISK sinalBrrisk;

    @ManyToOne 
    @JoinColumn(name = "checagemSensorId", referencedColumnName = "id") 
    private ChecagemSensorEntity checagemSensor;

    @Column(name="rota", nullable = false)
    @Enumerated(EnumType.STRING)
    private statusRota rota;

    @Enumerated(EnumType.STRING)
    @Column(name="sm", nullable = false)
    private statusSM sm;

}
