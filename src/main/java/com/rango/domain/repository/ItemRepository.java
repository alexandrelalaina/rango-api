package com.rango.domain.repository;

import com.rango.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query( value = "SELECT i.* " +
                    "FROM item i " +
                    "   , receita_item ri " +
                    "WHERE ri.fk_item_id = i.id " +
                    " AND ri.fk_receita_id = ?1 "
    , nativeQuery = true)
    public List<Item> getItemListByReceitaId(Integer receitaId);

}
