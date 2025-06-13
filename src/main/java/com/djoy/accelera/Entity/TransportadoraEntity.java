package com.djoy.accelera.Entity;

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
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="transportadoras")
@Data
public class TransportadoraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idPessoaJuridica", referencedColumnName = "id") 
    private PessoaJuridicaEntity pessoaJuridica;

    @Column 
    private String tipoServico;

    @Column 
    @Enumerated(EnumType.STRING)
    private tipoVinculo tipoVinculo;
    
}
