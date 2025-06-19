package com.djoy.accelera.DTO;

import com.djoy.accelera.Entity.PessoaEntity;

public record InserirVeiculoDTO(PessoaEntity proprietario, String modelo, String chassi, String cor, String placa, String placaCarreta) {

}
