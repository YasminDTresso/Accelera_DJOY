package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Key.UsuarioTransportadoraKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="usuariosTransportadoras")
@Data
public class UsuarioTransportadoraEntity {

    @EmbeddedId
    private UsuarioTransportadoraKey id;

}
