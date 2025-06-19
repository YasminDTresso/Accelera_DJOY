package com.djoy.accelera.DTO;

import com.djoy.accelera.Entity.PessoaEntity;
import com.djoy.accelera.Entity.VeiculoEntity;

public record EditarVeiculoDTO(PessoaEntity proprietario, String modelo, String chassi, String cor, String placa, String placaCarreta) {

}
