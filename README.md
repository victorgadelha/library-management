---

# 📚 LIB+

Uma API RESTful para gerenciar livros, autores, empréstimos e reservas em uma biblioteca.

<p align="center"> 
  <img src="https://img.shields.io/github/languages/top/victorgadelha/library-management" alt="GitHub top language"> 
  <img src="https://img.shields.io/badge/status-active-success.svg" alt="Status"> 
  <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="License"> 
  <img src="https://img.shields.io/badge/version-1.0.0-green.svg" alt="Version"> 
  <img src="https://img.shields.io/github/last-commit/victorgadelha/library-management" alt="GitHub last commit"> 
</p> 

<p align="center"> 
  <a href="https://skillicons.dev"> 
    <img src="https://skillicons.dev/icons?i=java,spring,maven" alt="My Skills"> 
  </a> 
</p>

## 🧾 Índice

- [Descrição](#-descrição)
- [Recursos](#-recursos)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Ambiente com Docker](#-ambiente-com-docker)
- [Endpoints](#-endpoints)
- [Exemplo de Uso](#-exemplo-de-uso)
- [Erros Comuns](#-erros-comuns)
- [Testes](#-testes)
- [Documentação Completa](#-documentação-completa)
- [Contribuição](#-contribuição)
- [Links Úteis](#-links-úteis)
- [Licença](#-licença)

## 📜 Descrição

A **LibraryAPI** permite que bibliotecas automatizem o gerenciamento de seu acervo de livros, controle de empréstimos e reservas. Construída usando **Spring Boot** e **H2**, a API foi projetada para ser escalável, segura e fácil de integrar.

## 🌟 Recursos

- Gerenciamento de livros (CRUD completo).
- Controle de autores associados a livros.
- Registro de empréstimos e reservas.
- Integração **JWT** para autenticação e autorização.
- Suporte a filtros para busca avançada de livros.

## 📦 Dependências Principais

- **Spring Boot Starter Web**: Para criar a API RESTful.
- **Spring Boot Starter Data JPA**: Integração com banco de dados.
- **Spring Boot Starter Security**: Autenticação e autorização.
- **Spring Boot Starter OAuth2 Resource Server**: Para autenticar solicitações protegidas com tokens JWT.
- **H2 Database**: Banco de dados em memória para desenvolvimento.
- **Java Faker**: Geração de dados fictícios para popular o banco.
- **Flyway**: Controle de versões para o banco de dados.
- **MapStruct**: Mapeamento entre DTOs e entidades.
- **Lombok**: Reduz boilerplate no código Java.
- **Spring Boot Starter Test**: Testes automatizados.

## 🔐 Configuração de Autenticação OAuth2 e Autorização

A API utiliza o **Spring Security** com **OAuth2 Resource Server** para proteger as rotas. Os tokens de acesso são baseados no padrão **JWT** (JSON Web Token) e a verificação do token é feita utilizando chaves pública e privada.

A aplicação implementa controle de autorização baseada em papéis (roles), com dois papéis principais:

- **USER**: Acesso a rotas gerais da API, como consulta de livros.
- **ADMIN**: Acesso a rotas de administração, como criação, edição e remoção de livros.

## ⚙️ Configuração Automática do Banco de Dados

Utilizamos o **Flyway** para aplicar migrações no banco de dados. Durante o desenvolvimento, o banco é populado automaticamente com dados fictícios usando o **Java Faker**.

## 🛠️ Instalação

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/library-api.git
cd library-management
```

2. Instale as dependências do projeto:

```bash
mvn clean install
```

3. Configure o banco de dados (consulte a seção Configuração).

4. Inicie o servidor:

```bash
mvn spring-boot:run
```

## ⚙️ Configuração

A aplicação já está configurada para usar o banco de dados **H2**. Não é necessário instalar ou configurar um banco externo. Por padrão, o **H2** opera no modo "memória" e os dados serão descartados ao reiniciar a aplicação.

Você pode acessar o console web do H2 para inspecionar os dados durante a execução da aplicação:

- Acesse: `http://localhost:8080/h2-console`
- Use as seguintes credenciais:
  - **URL JDBC**: `jdbc:h2:mem:testdb`
  - **Usuário**: `sa`
  - **Senha**: (deixe em branco)

Caso prefira usar um arquivo para persistência dos dados, ajuste a configuração no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:file:./data/library-management
spring.datasource.username=sa
spring.datasource.password=
```

## 📬 Endpoints

### Autenticação

| Método | Endpoint           | Descrição             | Autenticação |
|--------|--------------------|-----------------------|--------------|
| POST   | /auth/sign-in      | Autenticar usuário    | ❌           |

**Usuário Admin**:

Ao iniciar o projeto, já existe um usuário admin criado com as seguintes credenciais:

```json
{
  "email": "admin@email.com",
  "password": "Senha@123"
}
```

Essas credenciais podem ser usadas para fazer login como administrador.

### Livros

| Método | Endpoint        | Descrição                   | Autenticação     |
|--------|-----------------|-----------------------------|------------------|
| GET    | /books          | Listar todos os livros      | ✅ (ADMIN/USER)  |
| POST   | /books          | Adicionar um novo livro     | ✅ (ADMIN)       |
| GET    | /books/{id}     | Consultar detalhes de livro | ✅ (ADMIN/USER)  |
| PUT    | /books/{id}     | Atualizar informações       | ✅ (ADMIN)       |
| DELETE | /books/{id}     | Remover um livro            | ✅ (ADMIN)       |

## 🚀 Exemplo de Uso

### Autenticar Usuário

Envie um **POST** para `/auth/sign-in` com credenciais válidas.

**Resposta**:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

### Criar um Livro

Envie um **POST** para `/books` com o token:

```bash
curl -X POST "http://localhost:8080/books" \
-H "Authorization: Bearer {seu-token}" \
-H "Content-Type: application/json" \
-d '{"title":"1984","author":"George Orwell","language":"PT-BR"}'
```

**Resposta**:

```json
{
  "id": 1,
  "title": "1984",
  "author": "George Orwell",
  "language": "PT-BR"
}
```

## 🧪 Testes

Execute os testes com o Maven:

```bash
mvn test
```

## 🤝 Contribuição

Contribuições são bem-vindas!

1. Faça um fork deste repositório.
2. Crie uma branch para sua feature: `git checkout -b feature/nome-da-feature`.
3. Envie um Pull Request detalhando as mudanças.

## 🔗 Links Úteis

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [H2 Database Documentation](https://www.h2database.com/html/main.html)
- [JWT Overview](https://jwt.io/introduction/)

## 📄 Licença

Distribuído sob a licença **MIT**. Consulte o arquivo LICENSE para mais informações.

---
