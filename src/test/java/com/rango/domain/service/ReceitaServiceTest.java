package com.rango.domain.service;

import com.rango.utils.MyTestBase;
import com.rango.domain.model.Receita;
import com.rango.domain.repository.ReceitaCustomRepository;
import com.rango.domain.repository.ReceitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceitaServiceTest extends MyTestBase {

    ReceitaRepository mockReceitaRepository = mock(ReceitaRepository.class);
    ReceitaCustomRepository mockReceitaCustomRepository = mock(ReceitaCustomRepository.class);
    ReceitaService receitaService = new ReceitaService(mockReceitaRepository, mockReceitaCustomRepository);

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
        when(mockReceitaRepository.findAll()).thenReturn(receitaList);
        List<Receita> actual = receitaService.findAll();
        assertEquals(receitaList, actual);
    }

    @Test
    void findById() {
        when(mockReceitaRepository.findById(1)).thenReturn(Optional.of(receita1Salpicao));
        Receita actual = receitaService.findById(1);
        assertEquals(receita1Salpicao, actual);
    }

    @Test
    void add() {
        when(mockReceitaRepository.save(any(Receita.class))).thenReturn(receita1Salpicao);
        Receita actual = receitaService.add(receita1Salpicao);
        assertEquals(receita1Salpicao, actual);
    }

    @Test
    void filter() {

    }

    @Test
    void delete() {
        receitaService.delete(anyInt());
        assertTrue(true);
    }
}