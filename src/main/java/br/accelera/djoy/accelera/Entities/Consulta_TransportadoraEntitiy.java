package br.accelera.djoy.accelera.Entities;

import java.util.Calendar;

import br.accelera.djoy.accelera.Entities.Enums.tipoVinculo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="consulta_transportadoras")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class Consulta_TransportadoraEntitiy {

    //======Atributos/Colunas======    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transportadora_id", referencedColumnName = "id")
    private TransportadoraEntity transportadoraId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuarioId;

    @Column(name="objeto_da_consulta", length = 255, nullable = true)
    private String objetoConsulta;

    @Column(name="vinculo", nullable = true) 
    @Enumerated(EnumType.STRING)
    private tipoVinculo vinculo;

    @Column(name="status", length = 50, nullable = true)
    private String status;

    @Column(name="validade", nullable = true)
    private Calendar validade;

    @Column(name="observacao", nullable = true)
    private String observacao;

}
