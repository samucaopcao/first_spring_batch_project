# Sejam bem vindos(as) ao meu repositório : first_spring_batch_project


- :mortar_board: Este repositório foi construido a partir do Curso para desenvolvimento de Jobs com Spring Batch ministrado pela professora Giuliana Silva  
- :construction: Foi utilizado para construção as seguintes ferramentas: JDK 11, STS, DB MySQL.
- :hammer: Tecnologias do projeto : Spring Boot e Spring Batch.  
- :memo: Serão realizados pequenos Jobs para entendimento de conceitos. 

<h2>Algumas informações</h2>

Um Job é definido por uma sequência encadeada de Steps.
A forma na qual o Step define sua lógica o categoriza como:
<br><br><strong>-Tasklets :</strong> Executam tarefas pequenas, de pré processamento que normalmente precisam de um único comando para executar como limpeza de arquivos, criação de diretórios. A Tasklet executa repetidamente até um estado de completude.

<br><strong>-Chunks :</strong> Executam processamentos mais complexos que são realizados em pedaços que são divididos em tarefas de :
<br>ItemReader = Leitura 
<br>ItemPRocessor = Processamento
<br>ItemWriter = Escrita

Cada chunk possui sua própria transação, ou seja se ocorrer um erro em um dos chunk todo o trabalho realizado em um chunk anterior estará a salvo.


Ao criarmos e rodarmos um Job com conexão via BD, automaticamente tabelas serão criadas no DataBase que a aplicação foi apontada.
		Tabelas como:
		
<br><strong>batch_job_execution</strong> 
<br>*Essa tabela mostra quantas vezes o JOB executou seja com ERRO ou SUCESSO com sua hora inicial e final;

<br><strong>batch_job_execution_context</strong>
<br>*Essa tabela mostra quais dados foram salvos no contexto de execução do JOB, ou seja podemos mostrar aqui qualquer informação adicional que seja importante;

<br><strong>batch_job_execution_params</strong>

<br>*Essa tabela vai mostrar, para cada execução, qual parâmetro foi usado pelo Job;
<br><strong>batch_job_execution_seq</strong>

<br><strong>batch_job_instance</strong>
<br>*Essa tabela mostra quantas vezes o JOB executou com sucesso.

<br><strong>batch_job_seq</strong>

<br><strong>batch_step_execution</strong>
<br>*Essa tabela mostra quais Steps foram executados com seus Status (Erro ou Sucesso)

<br><strong>batch_step_execution_context</strong>
<br>*Essa tabela mostra quais dados foram salvos no contexto de execução do STEP, ou seja podemos mostrar aqui qualquer informação adicional que seja importante;

<br><strong>batch_step_execution_seq


