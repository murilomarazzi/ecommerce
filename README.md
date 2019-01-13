# Ecommerce

Projeto para estudo de micro serviços.

## Objetivo

O objetivo é que ao final deste projeto quem o criou consiga entender na prática os conceitos de micro serviços.

## Contexto

Durante uma entrevista a um candidato, houve um questionamento: "Como posso ter conhecimento prático de micro serviços, sem ter trabalhado com micro serviços?". Baseado nesse questionamento foi idealizado um projeto com regras de negócio simples, mas que ao mesmo tempo fosse possível aplicar a arquitetura de micro serviços.

## Critérios de aceite

O cliente deve ser capaz de:

- Realizar seu próprio cadastro
- Fazer login
- Listar todos os produtos
- Consultar Frete (Valor de entrega)
- Finalizar o pedido

Como o objetivo final do projeto é obter uma experiência prática com micro-serviços e não a criação de uma plataforma completa de ecommerce, algumas funcionalidades básicas de um ecommerce foram ignoradas, como por exemplo o cancelamento de pedidos, listagem de pedidos, etc...

Porém não há impedimento para a criação dessas funcionalidades, ou a devida integração com sistemas externos, de rastreio ou nota fiscal por exemplo. Acredito que com esses critérios de aceite e proposta de solução, todos os conceitos de micro-serviços podem ser estudados, sem a necessidade de tais integrações externas ou funcionalidades extras

# Resolução

A escolha de tecnologias é totalmente aberta a quem for desenvolver o projeto, porém é valido lembrar que a ideia é estudar e entender os conceitos de micro-serviços na prática, então escolha com cuidado e sabedoria. Para auxiliar aos que querem criar o projeto, há uma branch chamada [resolution](https://github.com/LeonardoFerreiraa/ecommerce/tree/resolution) que contem a solução do projeto seguindo a porposta de solução abaixo.

## Proposta de solução

# Realizar seu próprio cadastro

![flux1](https://github.com/LeonardoFerreiraa/ecommerce/raw/master/diagrams/flux1.png)

1. Cliente solicita cadastro
2. O serviço "Customers" recebe a request
3. Solicita a criação do usuário no serviço "OAuth"
4. O serviço "OAuth" valida e cadastra o usuário
5. O serviço "Customers" cadastra o usuário

# Fazer login

![flux2](https://github.com/LeonardoFerreiraa/ecommerce/raw/master/diagrams/flux2.png)

1. Cliente solicita autenticação no serviço "OAuth", passando suas credenciais
2. O serviço "OAuth", valida e autêntica o usuário

# Listar todos os produtos

![flux3](https://github.com/LeonardoFerreiraa/ecommerce/raw/master/diagrams/flux3.png)

1. O cliente solicita a lista de produtos
2. O serviço "Products" recebe a request
3. O serviço "Products" consulta e retorna os produtos previamente cadastrados

# Consultar Frete (Valor de entrega)

![flux4](https://github.com/LeonardoFerreiraa/ecommerce/raw/master/diagrams/flux4.png)

1. O cliente solicita a consulta de frete passando uma lista de produtos
2. O serviço "Shippings" recebe a requisição
3. O serviço "Products" é consultado para obter as dimensões dos produtos
4. O serviço "Products" consulta e retorna os dados previamente cadastrados
5. O serviço "Shipping" calcula a taxa de entrega e retorna ao usuário

# Finalizar o pedido

![flux5](https://github.com/LeonardoFerreiraa/ecommerce/raw/master/diagrams/flux5.png)

1. O cliente realiza o pedido passando como dados de entrada:
    - Token (OAuth)
    - Lista de produtos
    - ID do Endereço
    - Modo de envio
    - Tipo de pagamento
        - Se for credit_card
            - Número
            - CVV
            - Nome
            - Ano
            - Mês
2. O serviço "OAuth" é consultado para validação do usuário
3. O serviço "OAuth" valida o usuário
4. O serviço "Orders" recebe a requisição
5. O serviço "Orders" consulta os dados do usuário no serviço "Customers"
6. O serviço "Customers" consulta e retorna os dados previamente cadastrados
7. O serviço "Orders" consulta os dados dos produtos no serviço "Products"
8. O serviço "Products" consulta e retorna os dados previamente cadastrados
9. O serviço "Orders" salva as informações e solicita a notificação o usuário
10. O serviço "Orders" publica uma mensagem para o processsamento do pagamento
11. O serviço "Payments" consome a mensagem publicada
12. O serviço "Payments" processa a solicitação de pagamento
13. O serviço "Payments" publica o resultado do processamento
14. O serviço "Orders" consome o resultado do processamento do pagamento
    - Caso o processamento tenha ocorrido com sucesso, notifica o usuário e continua no fluxo normal
    - Caso o processamento tenha ocorrido sem sucesso, muda o status do pedido para cancelado e notifica o usuário
15. O serviço "Orders" publica uma mensagem para a criação da nota fiscal
16. O serviço "Invoices" consome a mensagem publicada
17. O serviço "Invoices" processa a nota fiscal
18. O serviço "Invoices" publica uma mensagem notificando a nota fiscal criada
19. O serviço "Orders" consome a mensagem, troca o status e notifica o usuário
20. O serviço "Shippings" consome a mensagem
21. O serviço "Shippings" registra um novo frete
22. O serviço "Shippings" notifica o usuário sobre o código de rastreio
23. É feito um processo que de tempos em tempos consulta o status da entrega e notifica o usuário caso haja novos eventos de rastreio
24. Quando o envio estiver no status de entregue, publica uma mensagem para a finalização do pedido
25. O serviço "Orders" consome a mensagem altera o status do pedido para entregue e notifica o usuário

As integrações externas dos serviços "Shipping", "Invoices" e "Payments", foram simplesmente ignoradas a fim de minimizar dores de cabeça (hehehe), os conceitos sobre retentativa e resiliencia podem ser praticados entre os serviços do sistema, tornando assim as integrações externas desnecessárias para o objetivo final do projeto.
