package br.accelera.djoy.accelera.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transportadoras")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class TransportadoraEntity {

    //======Atributos/Colunas======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int id;

    @Column(name="nome", length = 100, nullable = false)
    private String nome;

    @Column(name="fixo", nullable = false)
    private Integer fixo;

    @Column(name="agregado", nullable = false)
    private Integer agregado;

    @Column(name="terceiro", nullable = false)
    private Integer terceiro;

    @Column(name="consulta_fixo", nullable = false)
    private Integer consultaFixo;

    @Column(name="consulta_agregado", nullable = false)
    private Integer consultaAgregado;

    @Column(name="consulta_terceiro", nullable = false)
    private Integer consultaTerceiro;

}
