package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.UsuarioTransportadoraEntity;
import br.accelera.djoy.accelera.Entities.UsuarioTransportadoraKey;

@Repository
public interface UsuarioTransportadoraRepository extends JpaRepository<UsuarioTransportadoraEntity, UsuarioTransportadoraKey>{


}
