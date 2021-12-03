package com.diego.primeiroprojetospringbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diego.primeiroprojetospringbatch.dominio.Cliente;



@Configuration
public class JdbcPagingWriterConfig {
	@Bean
	public ItemWriter<Cliente> jdbcPagingWriter() {
		return items -> items.forEach(System.out::println);
	}
}
