package com.udemy.firstbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstbatchApplication {
	
	// Não é possível rodar duas vezes o mesmo job quando estamos
	// salvando no bando de dados, para isso devemos alterar algum
	// parâmetro seja no Run as -> Argmunents colocando por ex.:
	// nome=Samuel assim será um novo parâmetro e executará ou para 
	// resolver usamos o .incrementer(new RunIdIncrementer()) adicionando 
	// um RunId ou resumindo , um novo parâmetro. Porém tal tag impede a 
	// reinicialização do job do mesmo ponto caso algo dê errado.
	public static void main(String[] args) {
		SpringApplication.run(FirstbatchApplication.class, args);
	}

}
