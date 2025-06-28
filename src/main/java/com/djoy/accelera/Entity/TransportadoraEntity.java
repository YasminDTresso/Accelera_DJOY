package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Enum.tipoVinculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="transportadoras")
@Data
public class TransportadoraEntity extends PessoaJuridicaEntity{

    @Column 
    private String tipoServico;

    @Column 
    @Enumerated(EnumType.STRING)
    private tipoVinculo tipoVinculo;
    
}
