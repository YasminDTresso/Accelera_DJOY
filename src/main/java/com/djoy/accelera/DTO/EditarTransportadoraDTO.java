package com.djoy.accelera.DTO;

import java.sql.Date;

public record  EditarTransportadoraDTO( 
        String cnpj,
        Date dataNascimento,
        String telefone,
        String nome,
        String nomeFantasia,
        String email, 
        String tipoServico, 
        String tipoVinculo) {

}
