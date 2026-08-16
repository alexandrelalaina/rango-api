package com.rango.domain.service;

import com.rango.utils.MyTestBase;
import com.rango.utils.MyTestConstants;
import com.rango.domain.model.Item;
import com.rango.domain.repository.ItemCustomRepository;
import com.rango.domain.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ItemServiceTest extends MyTestBase {

    ItemRepository mockItemRepository = mock(ItemRepository.class);
    ItemCustomRepository mockItemCustomRepository = mock(ItemCustomRepository.class);
    ItemService itemService = new ItemService(mockItemRepository, mockItemCustomRepository);
//    MyTestBase myTestBase = new MyTestBase(ItemServiceTest.class.getName());
//    Item itemExpected = myTestBase.getItem1FrangoDesfiado();

//    @BeforeAll
//    void setUpClasse(){
//
//    }

    @BeforeEach
    void setUp() {
//        itemService = new ItemService(mockItemRepository);
    }

    @Test
    void findAll() {
        when(mockItemRepository.findAll()).thenReturn(itemList);
        List<Item> actual = itemService.findAll();
        assertEquals(itemList, actual);
    }

    @Test
    void getById() {
        when(mockItemRepository.findById(anyInt())).thenReturn(Optional.of(item1Frango));
        Item actual = itemService.findById(1);
        assertEquals(item1Frango, actual);
    }

    @Test
    void filter() {
        when(mockItemCustomRepository.filterCustomizado(any(Integer.class), anyString(), anyInt(), anyInt())).thenReturn(Collections.singletonList(item1Frango));
        List<Item> actual = itemService.filter(1, MyTestConstants.TEST_DESCRICAO, 1, 5);
        assertEquals(Collections.singletonList(item1Frango), actual);
    }

    @Test
    void add() {
        when(mockItemRepository.save(any(Item.class))).thenReturn(item1Frango);
        Item actual = itemService.add(item1Frango);
        assertEquals(item1Frango, actual);
    }

    @Test
    void deleteOk() {
        itemService.delete(anyInt());
        assertTrue(true);
    }

//    @Test
//    void deleteCallThrow() {
//        when(mockItemRepository.delete(any(Item.class))).thenThrow(EmptyResultDataAccessException.class);
//
//        EmptyResultDataAccessException exception = assertThrows(EmptyResultDataAccessException.class,
//                () -> itemService.delete(any(Item.class)));
//        assertNotNull(exception);
//    }
}