package com.djoy.accelera.Entity.Key;

import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class UsuarioTransportadoraKey {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", referencedColumnName = "pessoaFisicaId") 
    private UsuarioEntity usuarioId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadoraId", referencedColumnName = "idPessoaJuridica") 
    private TransportadoraEntity transportadoraId;

}
