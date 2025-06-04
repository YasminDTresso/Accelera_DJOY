package br.accelera.djoy.accelera.Entities.Dtos;

import br.accelera.djoy.accelera.Entities.Enums.tipoPermissao;

public record CriarUsuarioDto(
    String nome,
    String usuario,
    String senha,
    String cpf,
    tipoPermissao tipoPermissao
) {}
