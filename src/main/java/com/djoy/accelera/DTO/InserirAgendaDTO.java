package com.djoy.accelera.DTO;

import com.djoy.accelera.Entity.ChecagemSensorEntity;
import com.djoy.accelera.Entity.ConsultaEntity;
import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Entity.Enum.statusRota;
import com.djoy.accelera.Entity.Enum.statusSM;
import com.djoy.accelera.Entity.Enum.statusSinalBRRISK;
import com.djoy.accelera.Entity.Enum.statusSinalTCELL;

public record InserirAgendaDTO(TransportadoraEntity transportadora, PessoaFisicaEntity condutor,
                        VeiculoEntity veiculo, ConsultaEntity consulta, ChecagemSensorEntity checagemSensor, String observacao,
                         statusRota rota, statusSinalBRRISK sinalBRRISK, statusSinalTCELL sinalTCELL, statusSM SM) {

}
