package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Key.CondutorTransportadoraKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="condutoresTransportadoras")
@Data
public class CondutorTransportadoraEntity {

    @EmbeddedId
    private CondutorTransportadoraKey id;

}
