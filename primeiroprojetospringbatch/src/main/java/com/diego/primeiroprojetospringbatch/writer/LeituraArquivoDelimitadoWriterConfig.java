package com.diego.primeiroprojetospringbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diego.primeiroprojetospringbatch.dominio.Cliente;



@Configuration
public class LeituraArquivoDelimitadoWriterConfig {
	@Bean
	public ItemWriter<Cliente> leituraArquivoDelimitadoWriter() {
		System.out.println("Imprimindo resultado do arquivo delimitado : \n");
		return items -> items.forEach(System.out::println);
	}
}
