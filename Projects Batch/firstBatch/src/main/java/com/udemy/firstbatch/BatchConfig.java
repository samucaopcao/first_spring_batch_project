package com.udemy.firstbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing // Esta anotação que permite que utilizemos injeção e utilize elementos do
						// Framework
@Configuration
public class BatchConfig {

	// Para criar jobs devemos utilizar Builders que já estão
	// inclusos no Spring Batch nesse caso usamos o JobBuilderFactory
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	// Para criar o Step também utilizamos Builders
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// O método abaixo estamos construindo um Job utilizando o
	// JobBuilderFactory e na linha 18 nomeamos o Job com
	// o mesmo nome do método
	@Bean
	public Job imprimeOlaJob() {
		return jobBuilderFactory
				// Informamos o nome do Job normalmente com o mesmo
				// nome do método
				.get("imprimeOlaJob")
				// Começaremos agora a parte da lógica startando um Step
				.start(imprimeOlaStep())
				// Incrementando automáticamente os Id de parâmetros(ver coment classe main)
				.incrementer(new RunIdIncrementer())
				// Construindo o Job
				.build();

	}

	public Step imprimeOlaStep() {
		return stepBuilderFactory.get("imprimeOlaStep")
				// Em steps podemos usar para tarefas simples Tasklets
				// já para tarefas complexas usamos outro termo
				// Colocamos o valor null pois o mesmo será capturado em tempo de execução
				// pelo método imprimeOlaTasklet
				.tasklet(imprimeOlaTasklet(null)).build();

	}

	@Bean
	// Colocamos essa anotação abaixo para que o método entre no contexto de um Step, ou seja,
	// quando o step estiver sendo criado o valor #{jobParameters['nome']} será preenchido dos
	// parâmetros adicionados no Run as -> Argmunents onde estará por ex.:
	// nome=Samuel
	@StepScope
	public Tasklet imprimeOlaTasklet(@Value("#{jobParameters['nome']}")String nome) {
		return new Tasklet() {

			// Como taskelet é uma interface precisamos implementar sua lógica
			// por isso o método abaixo chamado execute
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
					throws Exception {
				System.out.println(String.format("Olá, %s!", nome));

				// O método deve retornar um RepeatStatus
				return RepeatStatus.FINISHED;
			}
		};
	}
}
