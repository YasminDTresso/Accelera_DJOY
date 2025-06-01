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
@Table(name="logAgendas")
@Data
public class LogAgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int id;

    @ManyToOne
    @JoinColumn(name = "agendaId", referencedColumnName = "id", nullable = false)  
    private AgendaEntity agenda;

    @ManyToOne
    @JoinColumn(name = "transportadoraId", referencedColumnName = "idPessoaJuridica", nullable = false)   
    private TransportadoraEntity transportadora;

    @ManyToOne
    @JoinColumn(name = "usuarioAlteracaoId", referencedColumnName = "pessoaFisicaId", nullable = false) 
    private UsuarioEntity usuarioAlteracao;    

    @Column(name="dataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    // Atribuindo a data local
    @PrePersist
    protected void onCreate() {
        this.dataAlteracao = LocalDateTime.now();
    }  

    @ManyToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id")
    private VeiculoEntity veiculo;

    @ManyToOne 
    @JoinColumn(name = "consultaAnteriorId", referencedColumnName = "id") 
    private ConsultaEntity consultaAnterior;    

    @ManyToOne 
    @JoinColumn(name = "consultaNovaId", referencedColumnName = "id") 
    private ConsultaEntity consultaNova;    


    @ManyToOne 
    @JoinColumn(name = "condutorAnteriorId", referencedColumnName = "pessoaFisicaId") 
    private CondutorEntity condutorAnterior;

    @ManyToOne 
    @JoinColumn(name = "condutorNovoId", referencedColumnName = "pessoaFisicaId") 
    private CondutorEntity condutorNovo;

    @Column(columnDefinition = "TEXT")
    private String observacaoAnterior;

    @Column(columnDefinition = "TEXT")
    private String observacaoNova;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private statusSinalTCELL sinalTcellAnterior;

    @Column
    @Enumerated(EnumType.STRING)
    private statusSinalTCELL sinalTcellNovo;    
  
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private statusSinalBRRISK sinalBrriskAnterior;

    @Enumerated(EnumType.STRING)
    @Column
    private statusSinalBRRISK sinalBrriskNovo;    

    @ManyToOne 
    @JoinColumn(name = "checagemSensorAnteriorId", referencedColumnName = "id") 
    private ChecagemSensorEntity checagemSensorAnterior;

    @ManyToOne 
    @JoinColumn(name = "checagemSensorNovoId", referencedColumnName = "id") 
    private ChecagemSensorEntity checagemSensorNovo;    

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private statusRota rotaAnterior;

    @Column
    @Enumerated(EnumType.STRING)
    private statusRota rotaNova;    

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private statusSM smAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name="smNovo")
    private statusSM smNovo;    

}
