curl -o - -X POST http://host.docker.internal:8080/api/authors/2 \
-u "user:example" \
-H "Content-Type: application/json" \
-d '{
  "id": "5",
  "bio": "new super bio"
}' -s -o /dev/null -w "%{http_code}\n"
