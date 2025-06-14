package com.djoy.accelera.Entity;

import java.util.Date;

import com.djoy.accelera.Entity.Enum.tipoPermissao;

public record CadastroUsuarioDTO(String login, String senha, tipoPermissao tipoPermissao, String cpf, String nome, Date dataNascimento, String email, String telefone) {



}
