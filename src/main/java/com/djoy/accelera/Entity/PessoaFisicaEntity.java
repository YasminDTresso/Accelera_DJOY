package com.djoy.accelera.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pessoasFisicas")
@Data
public class PessoaFisicaEntity extends PessoaEntity{

    @Column(length = 11, columnDefinition = "CHAR(11)", nullable = false, unique = true)
    private String cpf;

}
