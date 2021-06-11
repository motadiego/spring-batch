package com.diego.primeiroprojetospringbatch.step;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Step imprimeParImparStep() {
		return stepBuilderFactory
				.get("imprimeParImparStep")
				.<Integer , String>chunk(1)
				.reader(contaAteDezReader())           // ler
				.processor(parOuImparProcessor())      // processa
				.writer(imprimeWriter()) 			   // imprime
				.build();
	}
		

	private IteratorItemReader<Integer> contaAteDezReader() {
		List<Integer> numerosDeUmAteDez = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		return new IteratorItemReader<Integer>(numerosDeUmAteDez.iterator());
	}
	
	
	private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
		return new FunctionItemProcessor<Integer, String>
		(item ->  item % 2 == 0 ? String.format("Item %s é par", item)  : String.format("Item %s é impar", item));
	}

	private ItemWriter<String> imprimeWriter() {
		return valores -> valores.forEach(System.out::println);
	}
}
