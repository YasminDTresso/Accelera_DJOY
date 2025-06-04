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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="trabalhos_pendentes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class Trabalho_PendenteEntity {

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

    @Column(name="data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;
    @PrePersist
    protected void onCreate() {
        this.dataInclusao = LocalDateTime.now();
    }

    @Column(name="vinculo", nullable = false) 
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculo;

    @Column(name="placa_veiculo", length = 20, nullable = false)
    private String placaVeiculo;

    @Column(name="placa_carreta", length = 50, nullable = true)
    private String placaCarreta;

    @Column(name="consulta", nullable = false)
    @Enumerated(EnumType.STRING)
    private etapaConsulta consulta;

    @Column(name="condutor", length = 100, nullable = false)
    private String condutor;

    @Column(name="observacao", nullable = true)
    private String observacao;

    @Column(name="sinal_tcell", nullable = false)
    @Enumerated(EnumType.STRING)
    private statusSinalTCELL sinalTcell;
  
    @Enumerated(EnumType.STRING)
    @Column(name="sinal_brrisk", nullable = false)
    private statusSinalBRRISK sinalBrrisk;

    @Column(name="check_list", nullable = false)
    @Enumerated(EnumType.STRING)
    private etapaCheckList checkList;

    @Column(name="rota", nullable = false)
    @Enumerated(EnumType.STRING)
    private statusRota rota;

    @Enumerated(EnumType.STRING)
    @Column(name="sm", nullable = false)
    private statusSM sm;

}
