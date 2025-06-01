package com.djoy.accelera.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="veiculos")
@Data
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "proprietarioId", referencedColumnName = "id")  
    private PessoaEntity proprieatario;

    @Column(nullable = false)
    private String modelo;

    @Column(length = 17, nullable = false, unique = true)
    private String chassi;

    @Column(length = 50, nullable = false)
    private String cor;

    @Column(length = 7, nullable = false, unique = true)
    private String placa;

    @Column(length = 10, nullable = false, unique = true)
    private String placaCarreta;

}
