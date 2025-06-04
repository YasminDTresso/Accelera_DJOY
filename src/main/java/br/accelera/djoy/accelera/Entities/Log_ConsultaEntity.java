package br.accelera.djoy.accelera.Entities;

import java.time.LocalDateTime;

import br.accelera.djoy.accelera.Entities.Enums.etapaCheckList;
import br.accelera.djoy.accelera.Entities.Enums.etapaConsulta;
import br.accelera.djoy.accelera.Entities.Enums.statusRota;
import br.accelera.djoy.accelera.Entities.Enums.statusSM;
import br.accelera.djoy.accelera.Entities.Enums.statusSinalBRRISK;
import br.accelera.djoy.accelera.Entities.Enums.statusSinalTCELL;
import br.accelera.djoy.accelera.Entities.Enums.tipoVinculo;
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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="log_consultas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class Log_ConsultaEntity {

    //======Atributos/Colunas======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transportadora_id", referencedColumnName = "id")    
    private TransportadoraEntity transportadoraId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")    
    private UsuarioEntity usuarioId;

    @Column(name="data_inclusao", nullable = true)
    private LocalDateTime dataInclusao;
    @PrePersist
        protected void onCreate() {
            this.dataInclusao = LocalDateTime.now();
        }
   
    @Column(name="vinculo", nullable = true) 
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculo;
    
    @Column(name="placa_veiculo", length = 10 , nullable = true)
    private String placaVeiculo;
    
    @Column(name="placa_carreta", length =50 , nullable = true)
    private String placaCarreta;
    
    @Column(name="consulta", nullable = true)
    @Enumerated(EnumType.STRING)
    private etapaConsulta consulta;
    
    @Column(name="condutor", length =100 , nullable = true)
    private String condutor;
    
    @Column(name="observacao", length = 200, nullable = true)
    private String observacao;

    @Column(name="sinal_tcell", nullable = true)
    @Enumerated(EnumType.STRING)
    private statusSinalTCELL sinalTcell;
 
    @Enumerated(EnumType.STRING)
    @Column(name="sinal_brrisk", nullable = true)
    private statusSinalBRRISK sinalBrrisk;
    
    @Column(name="check_list", nullable = true)
    @Enumerated(EnumType.STRING)
    private etapaCheckList checkList;
    
    @Column(name="rota", nullable = true)
    @Enumerated(EnumType.STRING)
    private statusRota rota;
    
    @Enumerated(EnumType.STRING)
    @Column(name="sm", nullable = true)
    private statusSM sm;
    
    @Column(name="data_finalizacao", nullable = true)
    private LocalDateTime dataFinalizacao;
    @PreUpdate
        protected void onUpdate() {
        this.dataFinalizacao = LocalDateTime.now();
    }

}
