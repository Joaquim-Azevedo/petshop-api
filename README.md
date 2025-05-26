# Petshop API

API REST desenvolvida com Spring Boot para gerenciar animais, donos e serviços de um petshop. Este projeto visa fornecer endpoints claros e organizados para realizar operações como cadastro de animais, associação a donos, e acesso aos dados de raças e tipos.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database (desenvolvimento)
- Maven

## Como Rodar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/Joaquim-Azevedo/petshop-api.git
```

3. Entre no diretório do projeto:

```bash
cd petshop-api
```

3. Execute com Maven:

```bash
./mvnw spring-boot:run
```

4. Acesse a API localmente:

- Base URL: [http://localhost:8080](http://localhost:8080)

5. Para documentação da API, acesse:

- SwaggerUI URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html/)

---

## Funcionalidades

- Cadastro e consulta de animais
- Cadastro e consulta de donos
- Associação de animal a dono
- Consulta de dono com seus animais
- Deleção e reativação de animais e donos
- Cadastro automático de tipo de animal e raça

---

## Documentação de API com Swagger

1. Com o projeto executado, acesse:

- SwaggerUI URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html/)

---

## Pontos de Melhoria

| Aspecto                       | Detalhe                                                                                                                                        |
| ----------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| Tratamento de Exceções        | Um `@ControllerAdvice` para lidar com erros de forma centralizada. Facilita na hora de depurar e encontrar erros.                              |
| Validações                    | Melhorar as validações dos DTOs com Spring Validation                                                                                          |
| Testes Automatizados          | Não há testes unitários ou de integração implementados, o que pode prejudicar a confiabilidade.                                                |
| Uso de `.get()` em `Optional` | O uso de `.get()` diretamente nos `Optional` precisa de validação com `.isPresent()` ou `orElseThrow()`.                                       |
| Nome de DTOs                  | Algumas classes como `AnimalRequest` são usadas para entrada e saída. Ideal separar como `AnimalRequest` (entrada) e `AnimalResponse` (saída). |
| Requisições aninhadas         | Algumas chamadas de repositório dentro de streams podem gerar N+1 queries. É bom revisar a performance com `@EntityGraph` ou `fetch join`.     |

---

## Melhorias Futuras

- [✅] Adicionar mais validações
- [ ] Criar testes com JUnit + Mockito (ainda aprendendo sobre)
- [✅] Refatorar nomes dos DTOs
- [⏳] Implementar tratamento global de exceções (`@ControllerAdvice`)
- [✅] Melhorar requisições aninhadas
- [⏳] Adicionar serviços do petshop
- [✅] Melhorar uso dos `.get()` em `Optional`
- [✅] Documentação de API com Swagger UI

---

## Autor

Joaquim Azevedo  
[GitHub](https://github.com/Joaquim-Azevedo)

---

## Licença

Este projeto está sob a licença MIT.
