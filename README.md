# 🎓 Sistema de Gestão de Faculdade

Projeto Java com JDBC para gerenciamento de uma faculdade, permitindo operações CRUD para entidades como **Curso**, **Disciplina**, **Aluno** e **Funcionário**, utilizando o padrão DAO (Data Access Object).

---

## 📌 Objetivo do Projeto

Este sistema foi criado com fins educacionais para demonstrar a aplicação prática dos conceitos de Programação Orientada a Objetos (POO), JDBC e padrão de projeto DAO. Ele simula a rotina básica de uma instituição de ensino superior, controlando dados de cursos, disciplinas, alunos e funcionários.

---

## ⚙️ Funcionalidades

-  Cadastro de **Cursos**
-  Cadastro de **Disciplinas**
-  Cadastro de **Alunos**
-  Cadastro de **Funcionários**
-  Atualização e exclusão de registros
-  Listagem de todos os registros
-  Associação entre Curso e Disciplina
-  Separação em camadas: `model`, `dao`, `application`, `db`

---

## 🧰 Tecnologias Utilizadas

- Java (JDK 8+)
- JDBC (Java Database Connectivity)
- MySQL
- Padrão DAO
- IDE (Eclipse, IntelliJ IDEA)
- Git & GitHub


## 🛠️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/SilvaLeo1995/Sistema-Faculdade.git
cd Sistema-Faculdade
```

### 2️⃣ Configurar o banco de dados MySQL
Crie o banco de dados chamado faculdade

Execute o script SQL fornecido abaixo para criar as tabelas

Atualize o arquivo db.properties com seus dados de acesso:
```bash
properties
Copiar
Editar
dburl=jdbc:mysql://localhost:3306/faculdade
user=seu_usuario
password=sua_senha
```

### 3️⃣ Compilar e Executar
Pela IDE:
Abra o projeto na IDE (IntelliJ ou Eclipse)

Localize e execute a classe Program.java na pasta application

Pelo terminal:
```bash
javac -d bin src/**/*.java
java -cp bin application.Program
```

## 🗃️ Script SQL do Banco de Dados

```bash
CREATE DATABASE faculdade;
USE faculdade;

-- Tabela: Curso
CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela: Disciplina
CREATE TABLE disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Tabela: Aluno
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Tabela: Funcionario
CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50),
    salario DECIMAL(10,2)
);
```
### ⚠️ Atenção importante sobre a ordem dos cadastros:

Antes de cadastrar um aluno, o sistema exige que já existam:

- Pelo menos um **curso** cadastrado

- Pelo menos uma **disciplina** vinculada a esse **curso**

- Essa regra existe porque o aluno precisa estar associado a um curso, e a disciplina também depende dele.
Recomenda-se seguir esta ordem:

- Cadastrar **Curso**

- Cadastrar **Disciplina** (vinculada ao curso)

- Cadastrar **Aluno**


## 📚 Conceitos Aplicados
- DAO (Data Access Object): separa a lógica de acesso ao banco de dados

- JDBC: integração do Java com banco de dados relacional

- POO: uso de classes, herança, encapsulamento e polimorfismo

- Arquitetura em camadas: organização por pacotes (entidades, DAO, aplicação)

- Regra de Negócio: O cadastro de alunos só é permitido após o curso e a disciplina estarem devidamente cadastrados


## 👨‍💻 Autor
Desenvolvido por **Leonardo Gabriel Silva**

💻 GitHub: **SilvaLeo1995**

