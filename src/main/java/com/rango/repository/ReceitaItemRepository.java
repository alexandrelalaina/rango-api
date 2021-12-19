package com.rango.repository;

import com.rango.model.ReceitaItem;
import com.rango.model.ReceitaItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaItemRepository extends JpaRepository<ReceitaItem, ReceitaItemPK> {

}
