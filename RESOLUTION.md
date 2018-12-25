# Resolução

## Realizar seu próprio cadastro

## Fazer login

```sh
curl -X POST "http://localhost:8080/oauth/token" \
    -H "Accept: application/json" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -H "Authorization: Basic YXBpLWdhdGV3YXk6YXBpLWdhdGV3YXktc2VjcmV0" \
    -d "password=123123&username=leferreira&grant_type=password&client_id=api-gateway&client_secret=api-gateway-secret"
```

## Listar todos os produtos

```
curl http://localhost:8083/products \
    -H "Authorization: Bearer c344093e-5660-448e-b411-d8a2e315859d"
```

## Filtrar/Buscar os produtos por características

## Montar sua lista de compra

## Finalizar o pedido

## Listar e acompanhar seus pedidos
