package br.accelera.djoy.accelera.Entities.Dtos;

import br.accelera.djoy.accelera.Entities.Enums.tipoPermissao;

public record ObterUsuarioDto(
    Long id, 
    String nome,
    String usuario,
    String cpf, 
    tipoPermissao regra
) {}
