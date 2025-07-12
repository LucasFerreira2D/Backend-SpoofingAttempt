# Backend – Serviço de Detecção de Spoofing Facial

Este repositório contém o serviço **Backend** desenvolvido em **Spring Boot**, responsável por registrar, armazenar e fornecer relatórios de tentativas de spoofing facial em um banco de dados H2 embarcado.

---

## 📋 Sumário
- [Funcionalidades](#-funcionalidades)  
- [Tecnologias](#-tecnologias)  
- [Pré-requisitos](#-pré-requisitos)  
- [Instalação](#-instalação)  
- [Configuração](#-configuração)  
- [Execução](#-execução)  
- [Endpoints](#-endpoints)  
- [Acesso à Console H2](#-acesso-à-console-h2)  
- [Estrutura do Banco](#-estrutura-do-banco)  
- [Contribuição](#-contribuição)  
- [Licença](#-licença)

---

## 🚀 Funcionalidades
- Registro de tentativas de spoofing facial com dados:  
  - UUID da tentativa  
  - Data e hora (`TIMESTAMP`)  
  - Localização (latitude e longitude)  
  - Imagem em Base64 (armazenada como CLOB)  
  - (Opcional) Campos extras para relatórios: resultado da detecção, confiança, IP, dispositivo, etc.  
- Persistência em **H2** embarcado com modo file-based (`jdbc:h2:file:./data/banco_h2`).  
- Criação/atualização automática do esquema pelo **Hibernate** (`spring.jpa.hibernate.ddl-auto=update`).  
- Console web do H2 habilitada em `/h2-console` para inspeção manual.  
- API RESTful para:  
  - Criar novas tentativas (`POST`)  
  - Listar tentativas (`GET`)  
  - Gerar relatórios (filtros por usuário, data, local, etc.)

## 🛠 Tecnologias
- **Java 17**  
- **Spring Boot 3.5.3**  
- **Spring Data JPA**  
- **H2 Database** (arquivo local)  
- **Lombok**  
- **Maven** (ou Gradle)

## 📦 Pré-requisitos
- Java 17 SDK instalado  
- Maven 3.x (ou Gradle)  
- IDE recomendada: IntelliJ IDEA

## ⚙️ Instalação
1. Clone este repositório:  
   ```bash
   git clone https://github.com/seu-usuario/backend-spoofing.git
   cd backend-spoofing
