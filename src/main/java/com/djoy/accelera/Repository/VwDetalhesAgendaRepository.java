package com.djoy.accelera.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.AgendaEntity;
import com.djoy.accelera.Entity.Projection.AgendaDetalhadaProjection;

@Repository
public interface  VwDetalhesAgendaRepository extends JpaRepository<AgendaEntity, Integer>{

    @Query(value = "SELECT * FROM vw_DetalhesAgendasComVeiculos", nativeQuery = true)
    List<AgendaDetalhadaProjection> buscarTodosDetalhes();

    @Query(value = "SELECT * FROM vw_DetalhesAgendasComVeiculos WHERE RotaAgenda = :rota", nativeQuery = true)
    List<AgendaDetalhadaProjection> buscarPorRotaAgenda(@Param("rota") String rotaAgenda);
}
