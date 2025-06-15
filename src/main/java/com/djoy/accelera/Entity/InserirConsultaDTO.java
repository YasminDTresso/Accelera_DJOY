package com.djoy.accelera.Entity;

import java.time.LocalDateTime;

import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;

public record InserirConsultaDTO(UsuarioEntity usuarioInclusao, TransportadoraEntity transportadora, 
                        CondutorEntity condutor, VeiculoEntity veiculo, LocalDateTime validade, 
                        String observacao, statusEtapa status, tipoVinculo vinculo) {

}
