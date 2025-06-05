package com.djoy.accelera.Entity.Dtos;

import com.djoy.accelera.Entity.Enum.tipoPermissao;

public record CriarUsuarioDto(
    String usuario,
    String senha,
    tipoPermissao tipoPermissao
) {}