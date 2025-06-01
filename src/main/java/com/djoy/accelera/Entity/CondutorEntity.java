package com.djoy.accelera.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="condutores")
@Data
public class CondutorEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "pessoaFisicaId", referencedColumnName = "pessoaId")    
    private PessoaFisicaEntity id;

    @Column(length = 11, columnDefinition = "CHAR(11)", nullable = false, unique = true)
    private String cnh;
}
