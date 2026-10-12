# Checkpoint 2 — API de pedidos com Spring Boot

Projeto acadêmico desenvolvido com base no roteiro **Aplicação CRUD com Spring Boot**. A aplicação disponibiliza uma API REST para cadastrar, consultar, atualizar e excluir pedidos, com persistência em um banco de dados H2.

## Integrantes

| Nome completo | RM |
| --- | --- |
| Leonardo Fernandes Mesquita | 559623 |
| Vitor de Lima Domingues | 561008 |
| Giovanni Romano Provazi | 560434 |
| Caio Berardo de Araújo | 560357 |

**Turma:** 3ESPA  
**Disciplina:** SOA e Web Services

## Tecnologias

- Java 21.
- Spring Boot 4.1.1.
- Maven, com Maven Wrapper incluído no projeto.
- Spring Web para disponibilizar os endpoints REST.
- Spring Data JPA e Hibernate para persistência e mapeamento da entidade.
- H2 Database e console web do H2.
- Jakarta Validation para validação dos dados.
- Lombok para geração de getters, setters e construtores.
- Spring Boot DevTools para apoio ao desenvolvimento.
- Spring Boot Starter Test para execução dos testes.

## Funcionalidades

- Cadastrar um pedido.
- Listar todos os pedidos.
- Consultar um pedido pelo identificador.
- Atualizar o nome do cliente e o valor total de um pedido.
- Excluir um pedido.
- Validar o nome do cliente e o valor informado.
- Preencher a data atual antes da inserção quando a data não for informada.
- Retornar HTTP 404 nas operações por identificador quando o pedido não existir.

## Estrutura das pastas

Os caminhos abaixo são relativos à raiz do projeto, onde fica o arquivo `pom.xml`.

| Pasta ou arquivo | Responsabilidade |
| --- | --- |
| `pom.xml` | Dependências, versão do Java e configuração do build Maven. |
| `mvnw` e `mvnw.cmd` | Execução do Maven Wrapper no Linux/macOS e Windows. |
| `.mvn/wrapper/` | Configuração do Maven Wrapper. |
| `src/main/java/br/com/fiap/checkpoint2/` | Pacote principal da aplicação. |
| `src/main/java/br/com/fiap/checkpoint2/Checkpoint2Application.java` | Classe principal que inicializa o Spring Boot. |
| `src/main/java/br/com/fiap/checkpoint2/controller/OrderController.java` | Recebe requisições HTTP e define as respostas da API. |
| `src/main/java/br/com/fiap/checkpoint2/service/OrderService.java` | Implementa as operações de cadastro, consulta, atualização e exclusão. |
| `src/main/java/br/com/fiap/checkpoint2/repository/OrderRepository.java` | Interface que estende `JpaRepository` para acesso ao banco. |
| `src/main/java/br/com/fiap/checkpoint2/model/OrderModel.java` | Entidade que representa os pedidos e suas validações. |
| `src/main/resources/application.properties` | Configurações da aplicação e do banco H2. |
| `src/test/java/br/com/fiap/checkpoint2/Checkpoint2ApplicationTests.java` | Teste de carregamento do contexto do Spring. |
| `target/` | Arquivos gerados pela compilação; não contém o código-fonte a ser editado. |

## Organização da aplicação

O `OrderController` recebe a requisição e chama o `OrderService`. O serviço utiliza o `OrderRepository` para consultar ou alterar os dados no H2. A classe `OrderModel` define a entidade persistida na tabela `Pedidos`.

O projeto usa o pacote `br.com.fiap.checkpoint2` e a classe principal `Checkpoint2Application`. A propriedade `spring.application.name` está definida como `Checkpoint1`, conforme a configuração solicitada no roteiro; ela não altera os pacotes nem os endpoints.

## Entidade e banco de dados

A anotação `@Table(name = "Pedidos")` define o nome da tabela. A configuração `spring.jpa.hibernate.ddl-auto=update` permite que o Hibernate crie ou atualize sua estrutura a partir da entidade.

| Atributo | Descrição |
| --- | --- |
| `id` | Identificador do pedido, gerado automaticamente com a estratégia `IDENTITY`. |
| `clientName` | Nome do cliente, validado com `@NotEmpty`. |
| `orderDate` | Data do pedido, representada por `LocalDate`. |
| `totalValue` | Valor total, representado por `BigDecimal`, validado com `@DecimalMin` e `@Positive`. |

O nome não pode ser nulo ou vazio. Quando informado, o valor total deve ser maior que zero. As anotações numéricas utilizadas não tornam o campo obrigatório por si só. O método anotado com `@PrePersist` atribui a data atual quando `orderDate` está nulo.

A atualização modifica apenas `clientName` e `totalValue`, preservando o identificador e a data do pedido existente.

O banco é armazenado em arquivo, por meio da URL `jdbc:h2:file:~/testdb`. O símbolo `~` representa a pasta do usuário que executa a aplicação. Os registros permanecem disponíveis entre reinicializações enquanto o arquivo do banco for preservado.

## Como executar

1. Instale e configure o JDK 21.
2. Abra a pasta do projeto na IDE ou no terminal.
3. No terminal, acesse a pasta que contém o `pom.xml`.
4. Confira o Java utilizado pelo Maven e inicie a aplicação.

### Linux ou macOS

```bash
bash mvnw -version
bash mvnw spring-boot:run
```

### Windows — PowerShell

```powershell
.\mvnw.cmd -version
.\mvnw.cmd spring-boot:run
```

O comando de versão deve indicar Java 21. O Maven Wrapper pode precisar de acesso à internet na primeira execução para baixar o Maven e as dependências.

Também é possível executar o método `main` da classe `Checkpoint2Application` pela IDE.

A API fica disponível em [http://localhost:8085/orders](http://localhost:8085/orders). Uma resposta `[]` indica que não existem pedidos cadastrados. A aplicação não inclui uma interface gráfica própria; as operações são realizadas pelos endpoints REST.

## Endpoints

URL base: `http://localhost:8085`.

Nas rotas, `{code}` representa o identificador do pedido e é recebido pelo parâmetro Java `id` por meio de `@PathVariable("code")`.

| Método HTTP | Rota | Operação | Resposta de sucesso |
| --- | --- | --- | --- |
| `POST` | `/orders` | Cadastrar pedido | `201 Created`, com o pedido salvo. |
| `GET` | `/orders` | Listar pedidos | `200 OK`, com uma lista, que pode estar vazia. |
| `GET` | `/orders/{code}` | Consultar pedido pelo ID | `200 OK`, com o pedido encontrado. |
| `PUT` | `/orders/{code}` | Atualizar nome e valor | `200 OK`, com o pedido atualizado. |
| `DELETE` | `/orders/{code}` | Excluir pedido | `204 No Content`, sem corpo de resposta. |

As operações por ID retornam `404 Not Found` quando o pedido não existe. Dados que violem as validações do corpo das requisições POST e PUT resultam em `400 Bad Request`.

## Exemplos de utilização

Com a aplicação em execução, os comandos abaixo podem ser usados em um terminal Bash com `curl`. Para atualizar, consultar ou excluir, substitua o ID `1` pelo identificador retornado no cadastro.

### Cadastrar

```bash
curl -i -X POST http://localhost:8085/orders \
  -H 'Content-Type: application/json' \
  -d '{"clientName":"Ana Silva","totalValue":150.00}'
```

O corpo não inclui `id`, pois ele é gerado pelo banco. A data foi omitida para que seja preenchida automaticamente. Para informar uma data, utilize o formato `AAAA-MM-DD` no campo `orderDate`.

### Listar

```bash
curl -i http://localhost:8085/orders
```

### Consultar pelo ID

```bash
curl -i http://localhost:8085/orders/1
```

### Atualizar

```bash
curl -i -X PUT http://localhost:8085/orders/1 \
  -H 'Content-Type: application/json' \
  -d '{"clientName":"Ana Souza","totalValue":200.00}'
```

### Excluir

```bash
curl -i -X DELETE http://localhost:8085/orders/1
```

## Consultar o banco pelo console H2

Com a aplicação em execução, acesse [http://localhost:8085/h2-console](http://localhost:8085/h2-console) e utilize as configurações locais do projeto:

| Campo | Valor |
| --- | --- |
| Driver Class | `org.h2.Driver` |
| JDBC URL | `jdbc:h2:file:~/testdb` |
| User Name | `sa` |
| Password | `password` |

Clique em **Connect** e execute:

```sql
SELECT * FROM PEDIDOS;
```

Não é necessário criar a tabela manualmente. A consulta ficará sem registros até que pedidos sejam cadastrados.

## Testes

Para executar os testes no Linux ou macOS:

```bash
bash mvnw test
```

No Windows — PowerShell:

```powershell
.\mvnw.cmd test
```

O teste `contextLoads()` verifica se o contexto da aplicação Spring consegue iniciar. Ele não testa individualmente os endpoints do CRUD. Os exemplos de requisições acima permitem conferir as operações manualmente.
