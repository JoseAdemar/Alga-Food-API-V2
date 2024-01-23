package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> todos() {
        return entityManager.createQuery("FROM Restaurante",Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return entityManager.find(Restaurante.class,id);
    }

    @Override
    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Restaurante restaurante) {
      entityManager.remove(restaurante);
    }
}
