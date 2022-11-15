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
				.possuiEstoque(4)
				.obs("sem sal")
				.build();
		itemRepository.save(itemFrangoDesfiado);

		Item itemCenoura = Item.builder()
				.descricao("Cenoura")
				.possuiEstoque(2)
				.build();
		itemRepository.save(itemCenoura);

		Item itemAbobrinha = Item.builder()
				.descricao("Abobrinha")
				.possuiEstoque(1)
				.build();
		itemRepository.save(itemAbobrinha);

		Item itemCalabresa = Item.builder()
				.descricao("Calabresa")
				.possuiEstoque(2)
				.build();
		itemRepository.save(itemCalabresa);

		Item itemCebola = Item.builder()
				.descricao("Cebola")
				.possuiEstoque(3)
				.build();
		itemRepository.save(itemCebola);

		// 2 Receita
		receitaRepository.deleteAll();

		Receita receitaSalpicao = Receita.builder()
				.descricao("Salpicão")
				.valor(BigDecimal.valueOf(15))
				.build();
		receitaRepository.save(receitaSalpicao);

		Receita receitaCalabresaAcebolada = Receita.builder()
				.descricao("Calabresa Acebolada")
				.valor(BigDecimal.valueOf(12))
				.build();
		receitaRepository.save(receitaCalabresaAcebolada);

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
