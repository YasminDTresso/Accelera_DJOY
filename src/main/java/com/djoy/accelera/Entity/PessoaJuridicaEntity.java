package com.djoy.accelera.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pessoasJuridicas")
@Data
public class PessoaJuridicaEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "pessoaId", referencedColumnName = "id")    
    private PessoaEntity id;

    @Column(length = 14, columnDefinition = "CHAR(14)", nullable = false, unique = true)
    private String cnpj;

    @Column(length = 150)   
    private String nomeFantasia;
}
