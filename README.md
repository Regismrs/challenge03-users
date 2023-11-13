# Desafio 3 - Aplicação SpringBoot para cadastro de usuários com endpoints protegidos por JWT, comunicação entre aplicações via RabbitMQ, bancos relacionais e não relacionais.

Este repositório contém a solução desenvolvida para o Desafio 3 do programa de bolsas com foco em Desenvolvimento Backend, Java SpringBoot e AWS, oferecido pela CompassUol.

## Descrição
O Desafio 3 envolve a criação de duas aplicações. A primeira é responsável pelo cadastro de usuários, persistência dos dados em um banco de dados relacional, criptografia das senhas e utilização de tokens JWT para proteger determinados endpoints da REST API. Os tokens são obtidos por meio do login dos usuários registrados na API. Além disso, os eventos gerados são enviados para o RabbitMQ para fins de "logging".

A segunda aplicação tem a função de consumir as mensagens provenientes do RabbitMQ e armazená-las em um banco de dados não relacional, utilizando o MongoDB.

## TL;DR
Se você tiver o docker instalado na sua máquina e apenas quiser dar uma conferida no projeto você pode clonar esse repositório e executar o aruqivo <code>docker-compose-demo.yml</code> que está na raiz do projeto. Ele irá baixar e configurar todas as dependências. Use os comandos abaixo:
```bash
# clone o repositorio
git clone http://github.com/regismrs/challenge03-users.git

# abra a pasta e execute o docker-compose-demo
docker-compose -f docker-compose-demo.yml up
```

## Funcionalidades Principais
 - Cadastro de usuários com persistência em banco de dados relacional
 - Criptografia de senhas
 - Utilização de tokens JWT para segurança de endpoints
 - Envio de eventos para o RabbitMQ para "logging"
 - Consumo de mensagens do RabbitMQ e armazenamento em um banco de dados não relacional (MongoDB)

## Requisitos
- Java Development Kit (JDK)
- Ambiente de Desenvolvimento SpringBoot
- Docker
  
    **ATENÇÃO**: Caso você não tenha ou não possa executar o docker compose será necessário ter os programas instalados e configurá-los manualmente no arquivo <code>application.yml</code> das aplicações ms-user e ms-notification.

  ### Dependências:
  - MySql
  - MongoDb
  - RabbitMQ
  
## Como Usar esse projeto
1. Clone este repositório
   ```bash
   git clone https://github.com/regismrs/challenge03-users.git
   ```
2. Navegue até o diretório da aplicação desejada
3. Execute o arquivo docker-compose na raiz do projeto para configurar todas as dependências.
   ```bash
    docker-compose up
   ```
4. Executar as aplicações

* Na pasta <code>./ms-user/src/main/resources/static</code> tem um arquivo de collections do Postman.
  
## Fluxo de Uso

- O usuário cria um novo user
- Faz login para obter um token JWT
- Utiliza o token para acessar os endpoints de consulta e alteração, informando o mesmo no header das requisições. Por exemplo:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhQGIuYyIsImlhdCI6MTY5OTg5NjU3OSwiZXhwIjoxNjk5OTAwMTc5fQ.0wtSjs2VMestwL0wkjFn_r0aD4rBlEVzs4GkPmUdMw8
```
- As notificações dos eventos são enviadas e lidas automaticamente.

## Coisas legais que podem ajudar você em algum projeto
- Implentação do Jwt 
- Conversão do objeto notification para JSON String (PubService)
- As diferenças entre os repositórios e entities dos bancos relacionais (/ms-user banco MySql) e não relacionais (/ms-notification banco MongoDb)
- Criação de exchanges e queues no Rabbit via arquivo de configuração (docker-compose)
- Configuração do MongoDb via arquivo de inicialização (docker-compose)
- Utilização de váriaveis de ambiente no docker-compose com fallback no application.yml (docker-compose)



    