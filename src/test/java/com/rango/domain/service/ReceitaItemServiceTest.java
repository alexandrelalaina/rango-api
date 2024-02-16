package com.rango.domain.service;

import com.rango.utils.MyTestBase;
import com.rango.domain.model.Item;
import com.rango.domain.model.ReceitaItem;
import com.rango.domain.repository.ItemRepository;
import com.rango.domain.repository.ReceitaItemRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceitaItemServiceTest extends MyTestBase {

    ReceitaItemRepository mockReceitaItemRepository = mock(ReceitaItemRepository.class);
    ItemRepository mockItemRepository = mock(ItemRepository.class);

    ReceitaItemService receitaItemService = new ReceitaItemService(mockReceitaItemRepository, mockItemRepository);

    @Test
    void findAll() {
        when(mockReceitaItemRepository.findAll()).thenReturn(receitaItemList);
        List<ReceitaItem> actual = receitaItemService.findAll();
        assertEquals(receitaItemList, actual);
    }

    @Test
    void add() {
        when(mockReceitaItemRepository.save(any(ReceitaItem.class))).thenReturn(receita1SalpicaoItem1Frango);
        ReceitaItem actual = receitaItemService.add(receita1SalpicaoItem1Frango);
        assertEquals(receita1SalpicaoItem1Frango, actual);
    }

    @Test
    void getByReceitaId() {
        when(mockItemRepository.getItemListByReceitaId(2)).thenReturn(itemList);
        List<Item> actual = receitaItemService.getItemListByReceitaId(2);
        assertEquals(itemList, actual);
    }
}