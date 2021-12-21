package com.springbatch.contasbancarias.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.contasbancarias.dominio.Conta;

import javax.sql.DataSource;

@Configuration
public class ImpressaoContaWriterConfig {
	@Bean
	public JdbcBatchItemWriter<Conta> impressaoContaWriter(@Qualifier("appDataSource") DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder<Conta>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("insert into conta (tipo, limite, cliente_id) values(:tipoContaNome, :limite, :clienteId)")
				.dataSource(dataSource)
				.build();
	}
}
