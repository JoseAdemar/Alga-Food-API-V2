package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante1 = new Restaurante();
        Restaurante restaurante2 = new Restaurante();

        restaurante1.setNome("Bom gosto");
        restaurante2.setNome("Carne na brasa");

        cadastroRestaurante.adicionar(restaurante1);
        cadastroRestaurante.adicionar(restaurante2);
    }
}
