package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId =  restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null){
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha" +
                    " com o código %d", cozinhaId));
        }
         restaurante.setCozinha(cozinha);
        return restauranteRepository.adicionar(restaurante);
    }

    public Restaurante atualizar( Long id, Restaurante restaurante){
       Restaurante buscaRestaurante = restauranteRepository.buscar(id);
       if (buscaRestaurante == null){
           throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de restaurante" +
                   "com o id %d", id));
       }
        Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
        if (cozinha == null){
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha" +
                    " com o código %d", cozinha.getId()));
        }

        BeanUtils.copyProperties(restaurante.getCozinha(), buscaRestaurante.getCozinha());
        buscaRestaurante.setCozinha(cozinha);

        BeanUtils.copyProperties(restaurante, buscaRestaurante);
        return restauranteRepository.adicionar(buscaRestaurante);

    }


    public List<Restaurante> listaRestaurante() {
        return restauranteRepository.todos();
    }

    public Restaurante getRestaurantePorId(Long id) {
        try {
            Restaurante restaurante = restauranteRepository.buscar(id);
            if (restaurante != null) {
                return restaurante;
            }
            return null;
        } catch (EntidadeNaoEncontradaException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante não encontrado %d ", id));
        }

    }
}
