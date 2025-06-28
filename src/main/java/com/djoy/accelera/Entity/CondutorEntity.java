package com.djoy.accelera.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="condutores")
@Data
public class CondutorEntity extends PessoaFisicaEntity{

    @Column(length = 11, columnDefinition = "CHAR(11)", nullable = false, unique = true)
    private String cnh;
}
