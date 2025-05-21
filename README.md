# Restaurant Reservation System 🍽️

Este é um sistema de reservas para restaurante desenvolvido com **Java + Spring Boot**, com suporte a operações **CRUD** para gerenciar mesas e reservas. Ideal para gerenciar horários e organizar os atendimentos em um restaurante.

## 🚀 Funcionalidades

- Criar, listar, editar e excluir **reservas**
- Criar, listar e excluir **mesas**
- Filtros por **nome**, **e-mail** e **data/hora**
- Integração com banco de dados **MySQL**
- API RESTful com suporte completo a métodos HTTP (`GET`, `POST`, `PUT`, `DELETE`)

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman (para testes)

## Estrutura do Projeto

reservas-system/
├── src/
├── pom.xml
├── .gitignore
└── README.md


## Configuração

Edite o arquivo `src/main/resources/application.properties` com suas configurações locais de banco:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080

## Como rodar o projeto

1. Clone o repositório: git clone https://github.com/seu-usuario/restaurant-reservation-system.git
2. Configure o banco MySQL e crie o banco restaurant_db
3. Rode a aplicação via sua IDE ou ./mvnw spring-boot:run
4. Acesse: http://localhost:8080/tables ou acesse: http://localhost:8080/tables

## Exemplos de endpoints

- GET /reservations
- POST /reservations
- PUT /reservations/{id}
- DELETE /reservations/{id}
- GET /tables
- POST /tables
- DELETE /tables/{id}

## Autor
Vitor [https://www.linkedin.com/in/vitorhasantos/]
