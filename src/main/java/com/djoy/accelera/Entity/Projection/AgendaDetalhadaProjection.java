package com.djoy.accelera.Entity.Projection;

import java.time.LocalDateTime;

public interface AgendaDetalhadaProjection {

    Integer getIdAgenda();
    LocalDateTime getDataInclusaoAgenda();
    LocalDateTime getDataAlteracaoAgenda();
    String getRotaAgenda();
    String getObservacaoAgenda();
    String getSinalBrrisk();
    String getSinalTcell();
    String getSm();

    String getModeloVeiculo();
    String getPlacaVeiculo();
    String getCorVeiculo();

    String getCnhCondutor();
    String getNomeCondutor();

    String getTipoServicoTransportadora();
    String getTipoVinculoTransportadora();

}
