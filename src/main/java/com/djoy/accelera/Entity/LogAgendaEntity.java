package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Enum.statusRota;
import com.djoy.accelera.Entity.Enum.statusSM;
import com.djoy.accelera.Entity.Enum.statusSinalBRRISK;
import com.djoy.accelera.Entity.Enum.statusSinalTCELL;
import com.djoy.accelera.Entity.Key.LogAgendaKey;

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
@Table(name="logAgendas")
@Data
public class LogAgendaEntity {

    @EmbeddedId
    private LogAgendaKey id;

    //  @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)   
    //  private int id;

    // @ManyToOne
    // @JoinColumn(name = "agendaId", referencedColumnName = "id", nullable = false)  
    // private AgendaEntity agenda;

    @ManyToOne
    @JoinColumn(name = "transportadoraId", referencedColumnName = "id", nullable = false)   
    private TransportadoraEntity transportadora;

    @ManyToOne
    @JoinColumn(name = "usuarioAlteracaoId", referencedColumnName = "id", nullable = false) 
    private UsuarioEntity usuarioAlteracao;     

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
    @JoinColumn(name = "condutorAnteriorId", referencedColumnName = "id") 
    private CondutorEntity condutorAnterior;

    @ManyToOne 
    @JoinColumn(name = "condutorNovoId", referencedColumnName = "id") 
    private CondutorEntity condutorNovo;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String observacaoAnterior;

    @Column(columnDefinition = "NVARCHAR(MAX)")
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
