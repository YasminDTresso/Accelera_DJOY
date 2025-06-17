package com.djoy.accelera.DTO;

import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.etapaChecagem;
import com.djoy.accelera.Entity.Enum.tipoVinculo;

public record EditarChecagemSensorDTO(ChecagemSensorEntity checagemSensor, UsuarioEntity usuarioAlteracao, VeiculoEntity veiculo, UsuarioEntity gestor, String validade,
                                        String problemaEquipamento, String observacao, etapaChecagem status, tipoVinculo vinculo) {

}
