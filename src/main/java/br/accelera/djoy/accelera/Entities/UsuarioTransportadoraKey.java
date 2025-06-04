package br.accelera.djoy.accelera.Entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
@Embeddable
public class UsuarioTransportadoraKey implements Serializable{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadora_id", referencedColumnName = "id")   
    private TransportadoraEntity transportadoraId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") 
    private UsuarioEntity usuarioId;
}
