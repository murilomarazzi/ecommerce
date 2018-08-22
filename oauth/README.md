```
curl -X POST -vu acme:acmesecret http://localhost:8080/oauth/token \
  -H "Accept: application/json" \
  -d "password=password" \
  -d "username=username" \
  -d "grant_type=password
```