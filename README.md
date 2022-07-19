# STUDENT CARD
Cartão de crédito para alunos da FIAP.


### Tecnologias Utilizadas

- Spring Batch 
   -> Solução para processamento batch.

- Spring Mail

- H2 Database
  -> Utilizamos o H2 Database por atender as necessidade de armazenamento proposto no nosso projeto. É um banco de dados embutido e de facil integração
  com o Spring. Integramos com o H2 utilizando Spring Data JPA, caso seja necessário utilizar outro solução de banco de dados, teremos a 
  possibilidade de migração sem grandes problemas. 

- Spring Data JPA

### Como rodar o projeto

```bash
./mvnw spring-boot:run
```

-  Acesse o swagger -> http://localhost:8080/swagger-ui/index.html

### Processameto do Arquivo
- O procesamento do arquivo esta configurado para ser executado na inicialização da aplicação.


### Acessar documentação da API
-  Acesse o swagger -> http://localhost:8080/swagger-ui/index.html


### Acessar console do banco de dados 
- Acesse -> http://localhost:8080/h2-console
- JDBC URL : jdbc:h2:mem:studant-card-db
- User: sa 
- Pass: password



### Endpoint para gerar massa de transações
Acionando o endpoint `​/transaction​/random-transations` é gerado até 10 transações para cada estudante cadastro na base.


### Endpoint para consulta extrato de transação de um estudante
Acionando o endpoint `​/transaction​/statement​/{studentId}` retorna todas as transações de um estudante.


