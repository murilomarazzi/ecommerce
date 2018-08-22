Login:

```
curl -X POST -vu api-gateway:api-gateway-secret http://localhost:8080/oauth/token \
  -H "Accept: application/json" \
  -d "password=123123" \
  -d "username=leferreira" \
  -d "grant_type=password" \
  -d "client_id=api-gateway" \
  -d "client_secret=api-gateway-secret"
```

Refresh Token

```
curl -X POST -vu api-gateway:api-gateway-secret http://localhost:8080/oauth/token \
    -H "Accept: application/json" \
    -d "grant_type=refresh_token" \
    -d "refresh_token=85bcea4f-0dc3-4318-ad35-8d08f95c66a8" \
    -d "client_id=api-gateway" \
    -d "client_secret=somos-gateway-secret"

```