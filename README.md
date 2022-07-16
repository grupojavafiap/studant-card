# student-card

### Como rodar o projeto

```bash
./mvnw spring-boot:run
```

-  Acesse o swagger -> http://localhost:8080/swagger-ui/index.html


### Tecnologias Utilizadas
- Java 11
- Spring Batch 
- Spring Mail
- H2 Database


### Processameto do Arquivo
- O procesamento do arquivo esta configurado para ser executado na inicialização da aplicação.


### Acessar documentação da API


### Acessar console do banco de dados 
- Acesse -> http://localhost:8080/h2-console
- JDBC URL : jdbc:h2:mem:studant-card-db
- User: sa 
- Pass: password


### Já implementado:

- Lógica para load do arquivo via spring batch
- entidades students e transactions com relacionamento
- Swagger api para as entidades 

### Falta fazer:

- Extrato
- Transformar o caminho absoluto do arquivo em um resource
- Swagger api para as entidades 
- Testes unitários e integrados
- Criar massa de transações 
- Melhorar documentação swagger
- Atualizar Readme com instruçoes para rodar o projeto e com a justificativa das ferramentas escolhidas

> Nesse projeto utilizamos como banco de dados o H2 por se tratar do banco de dados open source mais utilizado no mundo, com alta confiabilidade, performance e fácil utilização.
>
> Utilizamos também ... ... ... 