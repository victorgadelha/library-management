---

# 📚 **LIB+** 

Uma API RESTful para gerenciar livros, autores, empréstimos e reservas em uma biblioteca.

![Status](https://img.shields.io/badge/status-active-success.svg)  
![License](https://img.shields.io/badge/license-MIT-blue.svg)  
![Version](https://img.shields.io/badge/version-1.0.0-green.svg)  

---

## 🧾 **Índice**

1. [Descrição](#descrição)  
2. [Recursos](#recursos)  
3. [Pré-requisitos](#pré-requisitos)  
4. [Instalação](#instalação)  
5. [Configuração](#configuração)  
6. [Ambiente com Docker](#ambiente-com-docker)  
7. [Endpoints](#endpoints)  
8. [Exemplo de Uso](#exemplo-de-uso)  
9. [Erros Comuns](#erros-comuns)  
10. [Testes](#testes)  
11. [Documentação Completa](#documentação-completa)  
12. [Contribuição](#contribuição)  
13. [Links Úteis](#links-úteis)  
14. [Licença](#licença)  

---

## 📜 **Descrição**

A **LibraryAPI** permite que bibliotecas automatizem o gerenciamento de seu acervo de livros, controle de empréstimos e reservas. Construída usando Spring Boot e H2, a API foi projetada para ser escalável, segura e fácil de integrar.

---

## 🌟 **Recursos**

- Gerenciamento de livros (CRUD completo).  
- Controle de autores associados a livros.  
- Registro de empréstimos e reservas.  
- Integração JWT para autenticação e autorização.  
- Suporte a filtros para busca avançada de livros.  

---

## 📦 **Dependências Principais**

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

---

## 🔐 **Configuração de Autenticação OAuth2 e Autorização**

A API utiliza o **Spring Security** com **OAuth2 Resource Server** para proteger as rotas. Os tokens de acesso são baseados no padrão JWT (JSON Web Token) e a verificação do token é feita utilizando chaves pública e privada.

Além disso, a aplicação implementa controle de **autorização baseada em papéis (roles)**, com dois papéis principais:
- **USER**: Acesso a rotas gerais da API, como consulta de livros.
- **ADMIN**: Acesso a rotas de administração, como criação, edição e remoção de livros.

---

## ⚙️ **Configuração Automática do Banco de Dados**

Utilizamos o **Flyway** para aplicar migrações no banco de dados. Durante o desenvolvimento, o banco é populado automaticamente com dados fictícios usando o **Java Faker**. 

---

## 🛠️ **Instalação**

1. Clone este repositório:  
   ```bash
   git clone https://github.com/seu-usuario/library-api.git
   cd library-management
   ```

2. Instale as dependências do projeto:  
   ```bash
   mvn clean install
   ```

3. Configure o banco de dados (consulte a seção [Configuração](#configuração)).

4. Inicie o servidor:  
   ```bash
   mvn spring-boot:run
   ```

---

## ⚙️ **Configuração**

A aplicação já está configurada para usar o banco de dados H2. Não é necessário instalar ou configurar um banco externo. Por padrão, o H2 opera no modo "memória" e os dados serão descartados ao reiniciar a aplicação.

Você pode acessar o console web do H2 para inspecionar os dados durante a execução da aplicação:

1. Acesse: `http://localhost:8080/h2-console`.
2. Use as seguintes credenciais:
   - **URL JDBC**: `jdbc:h2:mem:testdb`
   - **Usuário**: `sa`
   - **Senha**: *(deixe em branco)*

Caso prefira usar um arquivo para persistência dos dados, ajuste a configuração no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:h2:file:./data/library-management
spring.datasource.username=sa
spring.datasource.password=
```

---

## 📬 **Endpoints**

### **Autenticação**

| Método | Endpoint         | Descrição                  | Autenticação |
|--------|------------------|----------------------------|--------------|
| POST   | `/auth/sign-in`  | Autenticar usuário         |     ❌       |

---

### **Livros**

| Método | Endpoint              | Descrição                        | Autenticação           |
|--------|-----------------------|----------------------------------|------------------------|
| GET    | `/books`              | Listar todos os livros           | ✅ (ADMIN/USER)         |
| POST   | `/books`              | Adicionar um novo livro          | ✅ (ADMIN)              |
| GET    | `/books/{id}`         | Consultar detalhes de um livro   | ✅ (ADMIN/USER)         |
| PUT    | `/books/{id}`         | Atualizar informações de um livro| ✅ (ADMIN)              |
| DELETE | `/books/{id}`         | Remover um livro                 | ✅ (ADMIN)              |


USER: Acesso a endpoints como listar livros, consultar detalhes de livros.
ADMIN: Acesso completo, incluindo criação, atualização e remoção de livros.
---

## 🚀 **Exemplo de Uso**

1. **Autenticar Usuário**  
   Envie um POST para `/auth/login` com credenciais válidas.  
   Resposta:  
   ```json
   {
       "token": "eyJhbGciOiJIUzI1NiIs..."
   }
   ```

2. **Criar um Livro**  
   Envie um POST para `/books` com o token:  
   ```bash
   curl -X POST "http://localhost:8080/books" \
   -H "Authorization: Bearer {seu-token}" \
   -H "Content-Type: application/json" \
   -d '{"title":"1984","author":"George Orwell","languages":["en"],"downloadTotal":1245}'
   ```
   Resposta:  
   ```json
   {
       "id": 1,
       "title": "1984",
       "author": "George Orwell",
       "languages": ["en"],
       "downloadTotal": 1245
   }
   ```

---

## 🧪 **Testes**

Execute os testes com o Maven:  
```bash
mvn test
```

---

## 🤝 **Contribuição**

Contribuições são bem-vindas!  
1. Faça um fork deste repositório.  
2. Crie uma branch para sua feature: `git checkout -b feature/nome-da-feature`.  
3. Envie um Pull Request detalhando as mudanças.

---

## 🔗 **Links Úteis**

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)  
- [H2 Database Documentation](https://h2database.com/html/main.html)
- [JWT Overview](https://jwt.io/)  

---

## 📄 **Licença**

Distribuído sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais informações.

---
