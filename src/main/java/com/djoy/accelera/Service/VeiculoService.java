package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Repository.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class VeiculoService {

        @SuppressWarnings("unused")

    private final VeiculoRepository veiculoRepository;

    /*====Incluir===*/
    public VeiculoEntity incluir(VeiculoEntity veiculo){
        return veiculoRepository.save(veiculo);
    }

    /*====Editar===*/
    public VeiculoEntity editar(int id, VeiculoEntity veiculo){
        //Verifica se o registro existe
        Optional<VeiculoEntity> veiculoExistente = veiculoRepository.findById(id);

        if (veiculoExistente.isPresent()) {
            //Atualiza o registro
            VeiculoEntity veiculoAtualizado = veiculoExistente.get();

            // Atualiza os campos necessários
            veiculoAtualizado.setChassi(veiculo.getChassi());
            veiculoAtualizado.setCor(veiculo.getCor());
            veiculoAtualizado.setModelo(veiculo.getModelo());
            veiculoAtualizado.setPlaca(veiculo.getPlaca());
            veiculoAtualizado.setPlacaCarreta(veiculo.getPlacaCarreta());
            veiculoAtualizado.setProprietario(veiculo.getProprietario());

            // Salva o registro atualizado
            return veiculoRepository.save(veiculoAtualizado);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<VeiculoEntity> listarTodos(){
        return veiculoRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        veiculoRepository.deleteById(id);
    } 

}
