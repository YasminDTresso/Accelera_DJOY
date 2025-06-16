package com.djoy.accelera.DTO;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;

public record EditarConsultaDTO(CondutorEntity condutor, VeiculoEntity veiculo, String validade, String observacao, statusEtapa status, tipoVinculo vinculo) {

}
