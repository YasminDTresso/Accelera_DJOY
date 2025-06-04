package br.accelera.djoy.accelera.Entities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuario_transportadora")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class UsuarioTransportadoraEntity implements Serializable{

    @EmbeddedId
    private UsuarioTransportadoraKey id;

    //======Atributos/Colunas======
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Id
    private int id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadora_id", referencedColumnName = "id")   
    private TransportadoraEntity transportadoraId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") 
    private UsuarioEntity usuarioId;*/

}
