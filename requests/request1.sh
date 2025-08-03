curl -o - -X POST http://host.docker.internal:8080/api/authors/2/contact \
-u "user:example" \
-H "Content-Type: application/json" \
-d '{
  "subject": "subject",
  "message": "message"
}' -s -o /dev/null -w "%{http_code}\n"
