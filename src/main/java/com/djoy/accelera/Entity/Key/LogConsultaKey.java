package com.djoy.accelera.Entity.Key;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.djoy.accelera.Entity.ConsultaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Embeddable
@Data
public class LogConsultaKey implements Serializable{

    @ManyToOne
    @JoinColumn(name = "consultaId", referencedColumnName = "id", nullable = false)
    private ConsultaEntity consulta;

    @Column(name="dataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;
    @PrePersist
    protected void onCreate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}
