# CineBase API

**CineBAse** é uma API REST para gerenciamento e catálogo de filmes — inspirada no IMDb — desenvolvida com **Spring Boot** e **PostgreSQL**, com autenticação via **OAuth2 (JWT)**, documentação **Swagger** e testes automatizados.

---

## Objetivo

Criar uma API sólida, segura e escalável para manipulação de filmes, gêneros e avaliações, utilizando boas práticas de arquitetura e testes.

---

## Tecnologias

* Java 21
* Spring Boot 
* Spring Web
* Spring Data JPA
* Spring Security + OAuth2 (JWT)
* PostgreSQL
* Lombok
* Springdoc OpenAPI (Swagger UI)
* JUnit 5 + Mockito
* Docker (opcional)

---

## Estrutura de Pacotes

```
com.shakalinux.cineverse
 ├── controller/
 ├── dto/
 ├── model/
 ├── repository/
 ├── security/
 ├── service/
 ├── exception/
 └── CineVerseApplication.java
```

---

##  Configuração do Banco (PostgreSQL)

**Crie o banco:**

```sql
CREATE DATABASE cineverse;
```

**Arquivo `application.yml`:**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cineverse
    username: 
    password: 
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate.format_sql: true
```

---

##  Etapas do Desenvolvimento

###  Planejamento Inicial

* [ ] Criar projeto com Spring Initializr
* [ ] Adicionar dependências principais
* [ ] Estruturar pacotes (controller, service, etc.)
* [ ] Configurar PostgreSQL e `application.yml`

---

### Modelagem de Dados

* [ ] `Genero.java` → id, nome
* [ ] `Filme.java` → id, titulo, descricao, ano, genero
* [ ] `Avaliacao.java` → id, usuario, nota, comentario, filme
* [ ] `User.java` e `Role.java` → autenticação e permissões
* [ ] Criar repositórios (`JpaRepository`)

---

### Segurança (OAuth2 + JWT)

* [ ] `SecurityConfig.java` → rotas públicas/privadas
* [ ] `JwtUtils.java` → geração e validação de tokens
* [ ] `UserDetailsServiceImpl.java` → carregar usuários
* [ ] Endpoints:

    * `POST /auth/register` — cadastro de usuário
    * `POST /auth/login` — autenticação e geração de token

---

###  Endpoints Principais

####  Filmes

| Método | Endpoint        | Acesso  | Descrição                 |
| ------ | --------------- | ------- | ------------------------- |
| GET    | `/filmes`       | Público | Lista todos               |
| GET    | `/filmes/{id}`  | Público | Detalhes                  |
| POST   | `/filmes`       | Privado | Adiciona novo             |
| PUT    | `/filmes/{id}`  | Privado | Atualiza                  |
| DELETE | `/filmes/{id}`  | Privado | Remove                    |
| GET    | `/filmes/top10` | Público | Top 10 mais bem avaliados |

#### Gêneros

| Método | Endpoint   | Acesso  | Descrição     |
| ------ | ---------- | ------- | ------------- |
| GET    | `/generos` | Público | Lista gêneros |
| POST   | `/generos` | Privado | Adiciona novo |

#### Avaliações

| Método | Endpoint                 | Acesso  | Descrição               |
| ------ | ------------------------ | ------- | ----------------------- |
| GET    | `/avaliacoes/filme/{id}` | Público | Lista avaliações        |
| POST   | `/avaliacoes`            | Privado | Registra nova avaliação |

---

### DTOs e Mapeamento

* [ ] Criar `FilmeDTO`, `GeneroDTO`, `AvaliacaoDTO`, `UserDTO`
* [ ] Usar `ModelMapper` ou conversores manuais
* [ ] Retornar DTOs nos controladores

---

### Tratamento de Erros

* [ ] `ResourceNotFoundException.java`
* [ ] `GlobalExceptionHandler.java` (retorna JSON padrão)

  ```json
  {
    "timestamp": "2025-11-01T20:00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Filme não encontrado"
  }
  ```

---

### Testes Automatizados

**Camadas testadas:**

* Service → regras de negócio
* Controller → endpoints REST

**Cenários para cada teste:**

1.  **Sucesso** — operação funciona
2.  **Erro** — lança exceção esperada
3.  **Alternativo** — resultado diferente mas válido

Exemplo:

```java
@Test
void buscarPorId_DeveLancarExcecaoQuandoNaoEncontrarFilme() {
    when(filmeRepository.findById(99L)).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> filmeService.buscarPorId(99L));
}
```

---

###  Documentação

* [ ] Adicionar Swagger:

  ```xml
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
  </dependency>
  ```
* [ ] Acessar `http://localhost:8080/swagger-ui.html`

---

###  Deploy e Extras

* [ ] Criar `Dockerfile` e `docker-compose.yml`
* [ ] Configurar logs e profiles (`dev`, `prod`)
* [ ] Adicionar cache com Redis (`@Cacheable`)
* [ ] Paginação e filtros (`/filmes?genero=Ação&ano=2024`)
* [ ] Upload de pôster de filmes (opcional)

---

## Boas Práticas

* Retornar DTOs, nunca entidades diretamente
* Usar `ResponseEntity` nos controllers
* Documentar endpoints no Swagger
* Testar via Postman com token JWT
* Fazer commits pequenos e descritivos

---

## Autor

**Henrique Rocha (Shakalinux)**
Desenvolvedor Java | Técnico em Desenvolvimento de Sistemas – SENAI Brasília
[GitHub](https://github.com/shakalinux)

---
