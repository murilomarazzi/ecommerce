# Ecommerce

Projeto para estudo de micro serviços.

## Objetivo

O objetivo é que ao final deste projeto quem o criou consiga entender na prática os conceitos de micro serviços.

## Contexto

Durante uma entrevista a um candidato, houve um questionamento: "Como posso ter conhecimento prático de micro serviços, sem ter trabalhado com micro serviços?". Baseado nesse questionamento foi idealizado um projeto com regras de negócio simples, mas que ao mesmo tempo fosse possível aplicar a arquitetura de micro serviços.

## Critérios de aceite

O cliente deve ser capaz de:

- Realzar seu próprio cadastro
- Fazer login
- Listar todos os produtos
- Filtrar/Buscar os produtos por características
- Montar sua lista de compra
- Finalizar o pedido
- Listar e acompanhar seus pedidos

# Resolução

A escolha de tecnologias é totalmente aberta a quem for desenvolver o projeto, porém é valido lembrar que a ideia é estudar e entender os conceitos de micro serviço na prática, então escolha com cuidado e sabedoria. Para auxiliar aos que querem criar o projeto, há uma branch chamada `resolution` que contem a resolução do projeto seguindo a porposta de arquitetura e tecnologias abaixo.

## Proposta de arquitetura

![architecture](https://github.com/LeonardoFerreiraa/ecommerce/raw/master/diagrams/architecture.png)

### FE

Como se trata de um projeto para estudo de micro serviços a parte de front-end não precisa ser desenvolvida (está no desenho apenas para exemplificar)

### Produtos

Deverá ser o projeto responsável pelo dominio de produtos, podendo cadastrar, listar e filtrar os produtos.

### Clientes 

Deverá ser o projeto responsável pelo dominio de clientes. Salvando os dados do cliente e suas informações de endereço e dados de contato.

### Transportadora

Deverá ser o projeto responsável pelo dominio das transportadoras. Deverá fazer o itermédio entre os transportadoras a qual o ecommerce terá suporte como Correios e outras transportadoras. Utilizado para simulação de frete.

### Pedidos

Deverá ser o projeto responsável pelo dominio de pedidos, contendo informações sobre o pedido e seus status.

## Proposta de tecnologias

- [Spring](https://spring.io)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [RabbitMQ](https://www.rabbitmq.com)
