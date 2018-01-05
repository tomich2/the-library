# Library REST

## Books
http://localhost:8080/pa165/rest/books/


### Get all
Method type: GET

example:
curl http://localhost:8080/pa165/rest/books/


### Get book by ID
Method type: GET
http://localhost:8080/pa165/rest/books/{id}
where id is id of book you want to get.

example:
curl http://localhost:8080/pa165/rest/books/1


### Create book
Method type: POST
example:
curl -H "Content-Type: application/json" -X POST -d '{"author":"New Author","title":"Book Title"}' http://localhost:8080/pa165/rest/books


### Update book
Method type: PUT
example:
curl -H "Content-Type: application/json" -X PUT -d '{"author":"New Author","title":"Updated Title"}' http://localhost:8080/pa165/rest/books/2

### Delete
Method type: DELETE
http://localhost:8080/pa165/rest/books/{id}
where id is id of book you want to delete.

example:
curl -X "DELETE" http://localhost:8080/pa165/rest/books/2