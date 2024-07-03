curl -o - -X POST 'http://host.docker.internal:8080/api/books/order' \
-u "user:example" \
-H "Content-Type: application/json" \
-d '{
  "isbn": "9780618640157",
  "quantity": 2,
  "customerId": 4
}' \
-s -o /dev/null -w "%{http_code}\n"
