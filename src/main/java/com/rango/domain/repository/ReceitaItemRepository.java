package com.rango.domain.repository;

import com.rango.domain.model.ReceitaItem;
import com.rango.domain.model.ReceitaItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaItemRepository extends JpaRepository<ReceitaItem, ReceitaItemPK> {

}
