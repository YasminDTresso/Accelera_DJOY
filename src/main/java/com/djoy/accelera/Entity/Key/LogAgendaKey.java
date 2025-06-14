package com.djoy.accelera.Entity.Key;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.djoy.accelera.Entity.AgendaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Embeddable
@Data
public class LogAgendaKey implements Serializable{

    @ManyToOne
    @JoinColumn(name = "agendaId", referencedColumnName = "id", nullable = false)  
    private AgendaEntity agenda;

    @Column(name="dataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    // Atribuindo a data local
    @PrePersist
    protected void onCreate() {
        this.dataAlteracao = LocalDateTime.now();
    } 

}
