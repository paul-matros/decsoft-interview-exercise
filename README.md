## Run application
### Docker
Requirements:
- installed docker

To run the application, execute the following command:
```shell
docker compose up --build
```

### No docker
Change jdbc urls to local postgres db instance and run app from `ExampleApplication` main method.  
Requests contains host - you might have to change it too.

## REST API
The application exposes several endpoints to manage book collection, create orders to buy them, etc.

## TODO
There are some requests located in the `requests` directory. They all should work, however there are some errors that make them or process unusable.   
Correct them, controllers and/or services to make whole process work.  
One does not simply 'correct' app - do as you fell as long as it's usable for end user and developers :)

- request1 should result in creating message for author from customer
- request2 - same as above
- request3 - we are updating author's bio with given id, but there is some error
- request4 - we are trying to get report with orders, however nothing returns
- request5 - it was written using mapstruct, however it does not use much of it - it could do more 
- request6 - it contains some error (deliberately), but it doesn't say much - it would be nice to be able to read what is problem actually

And maybe there are some more errors/bugs/code smells that were not found :)   
#### Make project usable, zip it into single file and send us back.