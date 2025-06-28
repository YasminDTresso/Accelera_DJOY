package com.djoy.accelera.Entity.Key;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.djoy.accelera.Entity.ChecagemSensorEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Embeddable
@Data
public class LogChecagemSensorKey implements Serializable{

    @ManyToOne
    @JoinColumn(name = "checagemSensorId", referencedColumnName = "id", nullable = false)
    private ChecagemSensorEntity checagemSensor;

    @Column(name="dataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    // Atribuindo a data local
    @PrePersist
    protected void onCreate() {
        this.dataAlteracao = LocalDateTime.now();
    }     

}
