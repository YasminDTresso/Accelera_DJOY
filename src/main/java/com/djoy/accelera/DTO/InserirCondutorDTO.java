package com.djoy.accelera.DTO;

import java.sql.Date;

public record InserirCondutorDTO(String cpf, String cnh, Date dataNascimento, String telefone, String nome, String email) {

}
