package com.diego.primeiroprojetospringbatch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
class ParImparBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Job imprimeParImparJob() {
		return jobBuilderFactory
				.get("imprimeParImparJob")
				.start(imprimeParImparStep())
				.incrementer(new RunIdIncrementer())
				.build();
	}


	private Step imprimeParImparStep() {
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
