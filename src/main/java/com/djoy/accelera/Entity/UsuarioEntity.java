package com.djoy.accelera.Entity;

import com.djoy.accelera.Entity.Enum.tipoPermissao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter 
@Data
public class UsuarioEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "pessoaFisicaId", referencedColumnName = "pessoaId")    
    private PessoaFisicaEntity id;
    
    @Column(nullable = false, unique = true)
    private String user;
    
    @Column(nullable = false)
    private String senha;

    @Column(name = "permissao", nullable = false)   
    @Enumerated(EnumType.STRING)
    private tipoPermissao tipoPermissao;

}
