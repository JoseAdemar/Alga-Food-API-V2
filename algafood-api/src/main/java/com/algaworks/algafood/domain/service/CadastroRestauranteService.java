package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listaRestaurante() {
        return restauranteRepository.todos();
    }

    public Restaurante getRestaurantePorId(Long id) {
        try {
            Restaurante restaurante = restauranteRepository.porId(id);
            if (restaurante != null) {
                return restaurante;
            }
            return null;
        } catch (EntidadeNaoEncontradaException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante não encontrado %d ", id));
        }

    }
}
