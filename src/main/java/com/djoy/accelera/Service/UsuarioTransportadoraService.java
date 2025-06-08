package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.UsuarioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.UsuarioTransportadoraKey;
import com.djoy.accelera.Repository.UsuarioTransportadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class UsuarioTransportadoraService {

        @SuppressWarnings("unused")

    private final UsuarioTransportadoraRepository usuarioTransportadoraRepository;

    /*====Incluir===*/
    public UsuarioTransportadoraEntity incluir(UsuarioTransportadoraEntity usuarioTransportadora){
        return usuarioTransportadoraRepository.save(usuarioTransportadora);
    }

    /*====Editar===*/
    public UsuarioTransportadoraEntity editar(UsuarioTransportadoraKey id, UsuarioTransportadoraEntity usuarioTransportadora){
        //Verifica se o registro existe
        Optional<UsuarioTransportadoraEntity> usuarioTransportadoraExistente = usuarioTransportadoraRepository.findById(id);

        if (usuarioTransportadoraExistente.isPresent()) {
            //Atualiza o registro
            UsuarioTransportadoraEntity usuarioTransportadoraAtualizada = usuarioTransportadoraExistente.get();

            // Atualiza os campos necessários
            usuarioTransportadoraAtualizada.setId(usuarioTransportadora.getId());

            // Salva o registro atualizado
            return usuarioTransportadoraRepository.save(usuarioTransportadoraAtualizada);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<UsuarioTransportadoraEntity> listarTodos(){
        return usuarioTransportadoraRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(UsuarioTransportadoraKey id){
        usuarioTransportadoraRepository.deleteById(id);
    }  

}
