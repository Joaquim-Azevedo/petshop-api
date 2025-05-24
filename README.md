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
cd petshop-api
```

2. Execute com Maven:

```bash
./mvnw spring-boot:run
```

3. Acesse a API localmente:

- Base URL: [http://localhost:8080](http://localhost:8080)

---

## Funcionalidades

- Cadastro de animais
- Cadastro e consulta de donos
- Associação de animal a dono
- Consulta de dono com seus animais
- Cadastro e consulta de raças e tipos de animais
- Cadastro de serviços (banho, tosa, consulta veterinária)
- Cálculo de preço de serviço com base nas características do animal

---

## Endpoints da API

### 🐾 Animais

| Método | Endpoint    | Descrição                                                                 |
| ------ | ----------- | ------------------------------------------------------------------------- |
| POST   | `/pet`      | Cadastrar um novo animal -> Necessário ter um dono préviamente cadastrado |
| GET    | `/pet`      | Listar todos os animais                                                   |
| GET    | `/pet/{id}` | Buscar animal por ID                                                      |

**Exemplo de requisição:**

```json
POST /animais
{
  "name": "Rex",
  "neutred": true,
  "breed": "Labrador",
  "ownerCpf": "12345678900"
}
```

---

### 👤 Donos

| Método | Endpoint       | Descrição                                          |
| ------ | -------------- | -------------------------------------------------- |
| POST   | `/owner`       | Cadastrar um novo dono                             |
| GET    | `/owner`       | Listar todos os donos                              |
| GET    | `/owner/{cpf}` | Buscar dono por CPF (com animais)                  |
| DELETE | `/owner/{cpf}` | Inativa (deleta) o usuário e todos os seus animais |

---

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

- [ ] Adicionar validações com `javax.validation`
- [ ] Criar testes com JUnit + Mockito
- [ ] Refatorar nomes dos DTOs
- [ ] Implementar tratamento global de exceções (`@ControllerAdvice`)
- [ ] Melhorar requisições aninhadas
- [ ] Adicionar serviços do petshop

---

## Autor

Joaquim Azevedo  
[GitHub](https://github.com/Joaquim-Azevedo)

---

## Licença

Este projeto está sob a licença MIT.
