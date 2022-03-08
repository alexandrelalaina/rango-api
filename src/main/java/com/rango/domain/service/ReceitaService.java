package com.rango.domain.service;

import com.rango.domain.exception.ReceitaNaoEncontradaException;
import com.rango.domain.model.Receita;
import com.rango.domain.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository repository;

    public List<Receita> findAll(){
        return repository.findAll();
    }

    public Receita getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ReceitaNaoEncontradaException(id));    }

    public Receita add(Receita receita){
        //receita.setId(null);
        return repository.save(receita);
    }

    public void delete(Integer id){
        try {
            Receita receita = repository.getById(id);
            repository.delete(receita);
        } catch (EmptyResultDataAccessException e) {
            throw new ReceitaNaoEncontradaException(id);
        }
    }

}
