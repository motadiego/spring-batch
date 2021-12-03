package com.diego.primeiroprojetospringbatch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diego.primeiroprojetospringbatch.dominio.Cliente;



@Configuration
public class JdbcCursorWriterConfig {
	
	@Bean
	public JdbcBatchItemWriter<Cliente> jdbcCursorWriter(@Qualifier("springDataSource") DataSource dataSource) {
		
		return new JdbcBatchItemWriterBuilder<Cliente>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Cliente>())
				.sql("insert into cliente_migrado(nome,sobrenome,idade,email) values(:nome , :sobrenome, :idade, :email)")
				.dataSource(dataSource)
		.build();
	}
}
