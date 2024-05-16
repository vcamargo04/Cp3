# revisaoapi

Projeto de revisão de API para os alunos do 2TDSPM da FIAP
## Tecnologias

 - [Java 17](https://docs.oracle.com/en/java/javase/17/)
 - [Gradle](https://gradle.org/)
 - [Spring Boot 3.2.5](https://spring.io/projects/spring-boot)
 - [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
 - [Spring Web Starter](https://docs.spring.io/spring-boot/reference/web/index.html)
 - [H2 Database](http://h2database.com/html/main.html)
 - [REST](https://ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)

## Ferramentas

 - [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
 - [Postman](https://www.postman.com/)
## API Reference

#### Obter todos os clientes (findAll)

```http
  GET /clientes
```

#### Obter cliente por id (findById)

```http
  GET /clientes/${id}
```

| Parâmetro | Tipo     | Descrição                         |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long`   | **Obrigatório**. Id do cliente a ser buscado |

#### Gravar cliente (save)

```http
  POST /clientes
```

Body:

```json
{
    "nome": "Exemplo",
    "email": "exemplo@email.com"
}
```

| Parâmetro | Tipo     | Descrição                         |
| :-------- | :------- | :-------------------------------- |
| `nome`    | `String` | Nome do cliente a ser gravado     |
| `email`   | `String` | E-mail do cliente a ser gravado   |

#### Atualizar cliente (update)

```http
  PUT /clientes/${id}
```

| Parâmetro | Tipo     | Descrição                         |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long`   | **Obrigatório**. Id do cliente a ser atualizado |

Body:

```json
{
    "nome": "Exemplo",
    "email": "exemplo@email.com"
}
```

| Parâmetro | Tipo     | Descrição                         |
| :-------- | :------- | :-------------------------------- |
| `nome`    | `String` | Nome do cliente a ser atualizado     |
| `email`   | `String` | E-mail do cliente a ser atualizado   |

#### Excluir cliente (delete)

```http
  DELETE /clientes/${id}
```

| Parâmetro | Tipo     | Descrição                         |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long`   | **Obrigatório**. Id do cliente a ser excluído |
