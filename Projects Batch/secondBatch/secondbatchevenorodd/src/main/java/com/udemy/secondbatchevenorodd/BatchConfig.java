package com.udemy.secondbatchevenorodd;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public Job imprimeParImparJob() {
		return jobBuilderFactory
				// Informamos o nome do Job normalmente com o mesmo
				// nome do método
				.get("imprimeParImparJob")
				// Começaremos agora a parte da lógica startando um Step
				.start(imprimeParImparStep())
				// Incrementando automáticamente os Id de parâmetros(ver coment classe main)
				.incrementer(new RunIdIncrementer())
				// Construindo o Job
				.build();
	}
	
	@Bean
	public Step imprimeParImparStep() {
		return stepBuilderFactory
				.get("imprimeParImparJob")
				// Chunk com valor 1 significa que ele tem apenas um registro por pedaço,
				// toda vez que um chunk é criado uma transação no BD é criada, ou seja 
				// o uso de memória deve ser considerado
				.<Integer,String>chunk(1)
				// O reader lê um pedaço de dado
				.reader(contaAteDez())
				// O processador pegará cada dado desse pedaço acima
				.processor(parOuImparProcessor())
				// O write vai escrever a coleção de dados processados 
				.writer(imprimeWriter())
				.build();

	}

	// O Reader deve retornar uma coleção
	public IteratorItemReader<Integer> contaAteDez() {
		List<Integer> numerosDeUmAteDez = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		return new IteratorItemReader<Integer>(numerosDeUmAteDez.iterator());
	}
	// Processor
	public FunctionItemProcessor<Integer,String> parOuImparProcessor(){
		return new FunctionItemProcessor<Integer , String>(item -> item % 2 == 0 ? String.format("Item %s é Par", item): String.format("Item %s é Ímpar", item));
	}

	// Writer
	public ItemWriter<String> imprimeWriter(){
		return itens -> itens.forEach(System.out::println);
	}
}
