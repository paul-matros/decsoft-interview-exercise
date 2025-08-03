curl -o - -X PATCH http://host.docker.internal:8080/api/authors/2 \
-u "user:example" \
-H "Content-Type: application/json" \
-d '{
  "bio": "new super bio"
}' -s -o /dev/null -w "%{http_code}\n"
