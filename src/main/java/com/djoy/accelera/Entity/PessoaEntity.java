package com.djoy.accelera.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pessoas")
@Data
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 150, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private String email;

    @Column(length = 25, nullable = false)
    private String telefone;
    
}
