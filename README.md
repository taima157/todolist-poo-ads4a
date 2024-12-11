# 📝 Todo List - POO ADS4A

Bem-vindo ao projeto **Todo List**, desenvolvido com **Java 17** e **Spring Boot**. Este é um microsserviço que utiliza **JWT** para autenticação e **Liquibase** para versionamento de banco de dados.

---

## ✅ Requisitos
- **Java 17**
- **Maven**
- Banco de dados (**PostgreSQL**)
- **Git**

---

## 🚀 Como Rodar o Projeto

### 1️⃣ Clonar o Repositório
Clone o projeto com o comando:
```bash
git clone https://github.com/taima157/todolist-poo-ads4a.git
cd todolist-poo-ads4a
```
### 2️⃣ Configurar Variáveis de Ambiente
Defina as variáveis de ambiente necessárias:

- DB_URL: URL do banco (ex.: jdbc:postgresql://localhost:5432/todolist)
- DB_USERNAME: Usuário do banco.
- DB_PASSWORD: Senha do banco.
- JWT_TOKEN_KEY: Chave secreta para autenticação JWT.

### 3️⃣ Rodar a Aplicação
Use os comandos abaixo:

``` bash
mvn clean install
mvn spring-boot:run
```

---

## 📦 Banco de Dados
O Liquibase aplicará os scripts de migração automaticamente. Eles estão no diretório:


```css
main/
└── resource/
    └── db/
        └── changes/
            ├── create-user.sql
            └── create-todo.sql
```

---

## 🛠️ Tecnologias
- Java 17
- Spring Boot
- Liquibase 
- JWT
- Banco de dados relacional PostgreSQL
