package com.djoy.accelera.Entity.Dtos;

import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.Enum.tipoPermissao;

public record ObterUsuarioDto(
    PessoaFisicaEntity id, 
    String usuario,
    tipoPermissao regra
) {}