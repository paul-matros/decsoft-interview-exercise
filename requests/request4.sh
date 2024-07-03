curl -o - -X GET 'http://host.docker.internal:8080/api/books/orders?dateFrom=2024-01-01&dateTo=2025-01-01' \
-u "user:example" \
-H "Content-Type: application/json" \
-s -o /dev/null -w "%{http_code}\n"
