package br.accelera.djoy.accelera.Entities;

import br.accelera.djoy.accelera.Entities.Enums.tipoPermissao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class UsuarioEntity {

    //======Atributos/Colunas======    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int id;

    @Column(name="nome", length = 100, nullable = false)
    private String nome;

    @Column(name="cpf", length = 14, nullable = false)
    private String cpf;

    @Column(name="senha", length = 255, nullable = false)
    private String senha;

    @Column(name="usuario", length = 50, nullable = false)
    private String usuario;

    @Column(name="permissao", nullable = false) 
    @Enumerated(EnumType.STRING)
    private tipoPermissao permissao;
}
