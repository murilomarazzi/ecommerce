# Resolução

## Realizar seu próprio cadastro

```sh
curl -X POST "http://localhost:8083/customers" \
    -H 'Content-Type: application/json' \
    -d '{ "name": "Leonardo Ferreira", "email": "mail@leonardoferreira.com.br", "phone": "(16) 123456789", "password": "123123", "birthday": "26/12/1995" }'
```

## Fazer login

```sh
curl -X POST "http://localhost:8080/oauth/token" \
    -H "Accept: application/json" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -H "Authorization: Basic YXBpLWdhdGV3YXk6YXBpLWdhdGV3YXktc2VjcmV0" \
    -d "password=123123&username=mail@leonardoferreira.com.br&grant_type=password&client_id=api-gateway&client_secret=api-gateway-secret"
```

## Listar todos os produtos

```sh
curl http://localhost:8083/products \
    -H "Authorization: Bearer c344093e-5660-448e-b411-d8a2e315859d"
```

## Filtrar/Buscar os produtos por características

## Finalizar o pedido

## Listar e acompanhar seus pedidos
