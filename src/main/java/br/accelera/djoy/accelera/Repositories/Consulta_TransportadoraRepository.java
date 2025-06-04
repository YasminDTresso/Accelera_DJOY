package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.Consulta_TransportadoraEntitiy;

@Repository
public interface Consulta_TransportadoraRepository extends JpaRepository<Consulta_TransportadoraEntitiy, Integer> {

}
