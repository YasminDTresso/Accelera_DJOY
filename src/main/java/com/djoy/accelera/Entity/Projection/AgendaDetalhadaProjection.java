package com.djoy.accelera.Entity.Projection;

import java.time.LocalDateTime;

public interface AgendaDetalhadaProjection {

    Integer getIdAgenda();
    LocalDateTime getDataInclusaoAgenda();
    LocalDateTime getDataAlteracaoAgenda();
    String getSlaDiasAtendimento();    
    String getRotaAgenda();
    String getObservacaoAgenda();
    String getSinalBrrisk();
    String getSinalTcell();
    String getSm();

    String getStatusChecagemSensor();    
    String getNomeCondutor();
    String getStatusConsultas();         

    String getPlacaVeiculo();         

}
