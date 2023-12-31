package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CozinhaRepositoryImpl  implements CozinhaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> todas(){
        return manager.createQuery("FROM Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha porId(Long id){
        return manager.find(Cozinha.class,id);
    }
    @Override
    @Transactional  //serve para salvar e atualizar
    public Cozinha adicionar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha){
        cozinha = porId(cozinha.getId());
        manager.remove(cozinha);
    }
}
