package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Entities.UsuarioTransportadoraEntity;
import br.accelera.djoy.accelera.Entities.UsuarioTransportadoraKey;
import br.accelera.djoy.accelera.Repositories.UsuarioTransportadoraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class UsuarioTransportadoraService {

    @SuppressWarnings("unused")
    @Autowired
    private final UsuarioTransportadoraRepository usuarioTransportadoraRepository;

    public UsuarioTransportadoraEntity incluir(UsuarioTransportadoraEntity usuarioTransportadora){
        return usuarioTransportadoraRepository.save(usuarioTransportadora);
    }

    public UsuarioTransportadoraEntity editar(UsuarioTransportadoraKey id, UsuarioTransportadoraEntity usuarioTransportadora){
        // Verifica se o registro existe
        Optional<UsuarioTransportadoraEntity> usuarioTransportadoraExistente = usuarioTransportadoraRepository.findById(id);

        if (usuarioTransportadoraExistente.isPresent()) {
            // Atualiza o registro
            UsuarioTransportadoraEntity usuarioTransportadoraAtualizado = usuarioTransportadoraExistente.get();

            // Atualiza os campos necessários
            usuarioTransportadoraAtualizado.setId(usuarioTransportadora.getId());

            // Salva o registro atualizado
            return usuarioTransportadoraRepository.save(usuarioTransportadoraAtualizado);
        }else{
            //Caso o registro não exista, retorna como nulo.
            return null;  
        }

    }

    public List<UsuarioTransportadoraEntity> listarTodos(){
        return usuarioTransportadoraRepository.findAll();
    }

    public void excluir(UsuarioTransportadoraKey id){
        usuarioTransportadoraRepository.deleteById(id);
    }
}
