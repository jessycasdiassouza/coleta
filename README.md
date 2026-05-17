# Coleta Inteligente

## Descrição do Projeto

O **Coleta Inteligente** é uma aplicação web desenvolvida para disponibilizar informações sobre coleta seletiva de resíduos para a população de uma determinada cidade.

A aplicação possui duas áreas principais:

### Área Pública
Permite que qualquer cidadão consulte:

- Pontos de coleta cadastrados;
- Tipos de resíduos aceitos em cada ponto de coleta;
- Informações de localização dos pontos de descarte.

### Área Administrativa
Permite que usuários autorizados da prefeitura realizem o gerenciamento do sistema, incluindo:

- Cadastro e manutenção de usuários administrativos;
- Cadastro e manutenção de tipos de resíduos;
- Cadastro e manutenção de pontos de coleta;
- Vinculação entre pontos de coleta e tipos de resíduos.

O sistema também possui autenticação com JWT para proteção das funcionalidades administrativas.

---

## Tecnologias Utilizadas

### Front-end
- Angular 21
- TypeScript
- HTML5
- CSS3

### Back-end
- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate

### Banco de Dados
- PostgreSQL

### Containerização
- Docker
- Docker Compose

---

## Execução da Aplicação

Para executar toda a aplicação, é necessário apenas possuir o:

- Docker Desktop

instalado na máquina.

Não é necessário instalar:

- Java;
- PostgreSQL;
- Angular CLI;
- Maven;
- IDEs de desenvolvimento.

Toda a aplicação (front-end, back-end e banco de dados) será executada através de containers Docker.

---

## Estrutura da Aplicação

A aplicação é composta pelos seguintes serviços:

|-------------------|------------------------------|
| Serviço           | Descrição                    |
|-------------------|------------------------------|
| Front-end Angular | Interface web do sistema     |
| API Spring Boot   | Serviços REST da aplicação   |
| PostgreSQL        | Banco de dados da aplicação  |
|-------------------|------------------------------|

---

## Funcionalidades Implementadas

### Área Administrativa
- Cadastro de usuários;
- Edição de usuários;
- Inativação de usuários;
- Cadastro de tipos de resíduos;
- Edição de tipos de resíduos;
- Exclusão de tipos de resíduos;
- Cadastro de pontos de coleta;
- Edição de pontos de coleta;
- Exclusão de pontos de coleta;
- Vinculação entre pontos de coleta e tipos de resíduos;
- Autenticação com JWT.

### Área Pública
- Consulta de pontos de coleta;
- Consulta dos resíduos aceitos em cada ponto;
- Filtros de consulta por bairro;
- Filtros de consulta por tipo de resíduo.

---

## Como Executar

Com o Docker Desktop em execução, utilizar o comando:

```bash
docker compose up --build
```

Após a inicialização dos containers:

|-----------|------------------------|
| Serviço   | URL                    |
|-----------|------------------------|
| Front-end | http://localhost:4200  |
| Back-end  | http://localhost:8080  |
|-----------|------------------------|

---

## Usuário Administrativo Inicial

O sistema cria automaticamente um usuário administrador padrão na primeira execução:

|--------|------------------|
| Campo | Valor             |
|--------|------------------|
| E-mail | admin@coleta.com |
| Senha  | 123456           |
|--------|------------------|

---

## Objetivo do Projeto

O objetivo do sistema é facilitar o acesso da população às informações sobre descarte correto de resíduos, incentivando práticas de coleta seletiva e contribuindo para ações de sustentabilidade e preservação ambiental.
