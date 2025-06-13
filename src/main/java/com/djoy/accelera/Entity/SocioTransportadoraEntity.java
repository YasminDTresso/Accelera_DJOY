package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Key.SocioTransportadoraKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="sociosTransportadoras")
@Data
public class SocioTransportadoraEntity {

    @EmbeddedId
    private SocioTransportadoraKey id;
    
}
