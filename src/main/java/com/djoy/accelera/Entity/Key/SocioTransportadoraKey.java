package com.djoy.accelera.Entity.Key;

import java.io.Serializable;

import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class SocioTransportadoraKey implements Serializable{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoaId", referencedColumnName = "id") 
    private PessoaEntity pessoaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadoraId", referencedColumnName = "id") 
    private TransportadoraEntity transportadoraId;

}
