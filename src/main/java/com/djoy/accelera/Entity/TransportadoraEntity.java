package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Enum.tipoVinculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="transportadoras")
@Data
public class TransportadoraEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "idPessoaJuridica", referencedColumnName = "pessoaId") 
    private PessoaJuridicaEntity id;

    @Column(name = "vinculo")   
    @Enumerated(EnumType.STRING)
    private tipoVinculo tipoVinculo;
    
}
