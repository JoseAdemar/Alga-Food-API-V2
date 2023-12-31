package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")//, produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> estados(){
        return estadoRepository.listar();
    }
}
