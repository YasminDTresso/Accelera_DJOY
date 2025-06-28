package com.djoy.accelera.DTO;

import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.etapaChecagem;
import com.djoy.accelera.Entity.Enum.tipoVinculo;

public record InserirChecagemSensorDTO(TransportadoraEntity transportadora, VeiculoEntity veiculo, UsuarioEntity gestor, String validade, String inicioProblema, 
                        String equipamento, String problemaEquipamento, String observacao, etapaChecagem status, tipoVinculo vinculo) {

}
