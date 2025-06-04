package br.accelera.djoy.accelera.Entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="controle_de_checklist")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class Controle_CheckListEntity {

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

    @Column(name="gestor", length = 255, nullable = true)
    private String gestor;

    @Column(name="placa", length = 20, nullable = true)
    private String placa;

    @Column(name="equipamento", length = 255, nullable = true)
    private String equipamento;

    @Column(name="vinculo", length = 255, nullable = true)
    private String vinculo;

    @Column(name="status_cl", length = 255, nullable = true)
    private String statusCl;

    @Column(name="validade", nullable = true)
    private Date validade;

    @Column(name="programacao", length = 255, nullable = true)
    private String programacao;

    @Column(name="problema_equipamento", length = 255, nullable = true)
    private String problemaEquipamento;

    @Column(name="inicio_problema", nullable = true)
    private Date inicioProblema;

    @Column(name="observacao", nullable = true)
    private String observacao;
    

}
