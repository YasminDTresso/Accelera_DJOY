package com.djoy.accelera.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pessoasJuridicas")
@Data
public class PessoaJuridicaEntity extends PessoaEntity{

    @Column(length = 14, columnDefinition = "CHAR(14)", nullable = false, unique = true)
    private String cnpj;

    @Column(length = 150)   
    private String nomeFantasia;
}
