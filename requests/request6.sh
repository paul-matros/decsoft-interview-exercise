curl -o - -X POST 'http://host.docker.internal:8080/api/orders' \
-u "user:example" \
-H "Content-Type: application/json" \
-d '{
  "customerId": 4,
    "orderItems": [
      {
        "isbn": "9780618640157",
        "quantity": 0
      }
    ]
}' \
-s -o /dev/null -w "%{http_code}\n"
