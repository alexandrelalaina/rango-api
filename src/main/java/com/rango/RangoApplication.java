package com.rango;

import com.rango.domain.model.Item;
import com.rango.domain.model.Receita;
import com.rango.domain.model.ReceitaItem;
import com.rango.domain.model.ReceitaItemPK;
import com.rango.domain.repository.ItemRepository;
import com.rango.domain.repository.ReceitaItemRepository;
import com.rango.domain.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RangoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RangoApplication.class, args);
	}

	@Bean
	CommandLineRunner cargaInicial(
			ItemRepository itemRepository,
			ReceitaRepository receitaRepository,
			ReceitaItemRepository receitaItemRepository){
		System.out.println("CommandLineRunner => cargaItem");

		// 1 Item
		// 2 Receita
		// 3 ReceitaItem

		//1 Item
		itemRepository.deleteAll();

		Item itemFrangoDesfiado = Item.builder()
				.descricao("Frango desfiado")
				.possuiEstoque(3)
				.build();
		itemRepository.save(itemFrangoDesfiado);

		Item itemCenoura = Item.builder()
				.descricao("Cenoura")
				.possuiEstoque(3)
				.build();
		itemRepository.save(itemCenoura);

		Item itemAbobrinha = Item.builder()
				.descricao("Abobrinha")
				.possuiEstoque(3)
				.build();
		itemRepository.save(itemAbobrinha);

		// 2 Receita
		receitaRepository.deleteAll();

		Receita receitaSalpicao = Receita.builder()
				.descricao("Salpícão")
				.valor(BigDecimal.valueOf(15))
				.build();
		receitaRepository.save(receitaSalpicao);

		// 3 ReceitaItem
		receitaItemRepository.deleteAll();

		receitaItemRepository.save(ReceitaItem.builder()
				.id(ReceitaItemPK.builder()
						.receitaId(receitaSalpicao)
						.itemId(itemFrangoDesfiado)
						.build())
				.build());

		receitaItemRepository.save(ReceitaItem.builder()
				.id(ReceitaItemPK.builder()
						.receitaId(receitaSalpicao)
						.itemId(itemCenoura)
						.build())
				.build());

		receitaItemRepository.save(ReceitaItem.builder()
				.id(ReceitaItemPK.builder()
						.receitaId(receitaSalpicao)
						.itemId(itemAbobrinha)
						.build())
				.build());

		return null;

	}

}
