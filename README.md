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

There are some requests located in the `requests` directory. They all should work, however there are some errors that
make them or process unusable.   
Correct them, controllers and/or services to make whole process work.  
One does not simply 'correct' app - do as you fell as long as it's usable for end user and developers :)

- request1 should result in creating message for author from customer
- request2 - same as above
- request3 - we are updating author's bio with given id, but there is some error
- request4 - we are trying to get report with orders, however nothing returns
- request5 - it was written using mapstruct, however it does not use much of it - it could do more
- request6 - it contains some error (deliberately), but it doesn't say much - it would be nice to be able to read what
  is problem actually

And maybe there are some more errors/bugs/code smells that were not found :)

#### Make project usable, create a repository and share it with us.

## Completed fixes and features (TODO resolution)

The following improvements and corrections have been applied to make the whole process usable for both end users and
developers:

### TODO resolution

#### Request 1 - Creating message for author from customer

Fixed JSON syntax error in the POST data payload in request1.sh, ensuring the request is correctly parsed and message
creation works as expected.

#### Request 2 - Similar message creation as request 1

Shortened the subject field in the payload in request2.sh to pass validation constraints, fixing validation errors that
blocked the message creation.

#### Request 3 - Updating author's bio by ID

Changed update endpoint to use HTTP PATCH instead of PUT to align with partial updates.

Removed id field from UpdateAuthorDTO and from the request3.sh to fix the error related to mismatched payload and path
variable.

#### Request 4 - Getting order report

Fixed the book_order_report database view to correctly use the actual order date instead of an incorrect or null date,
enabling the report to return data properly.

#### Request 5 - Use MapStruct more effectively

Refactored manual mappings in the book order mapping logic to leverage MapStruct mapping functions and features for
cleaner and more maintainable code.

#### Request 6 - Readable Error Messages on Failures

- Added a global exception handler that consistently handles validation and other exceptions.
- This makes error responses clear and informative, simplifying troubleshooting.
- The handler also catches issues from the original requests 1 and 2, providing correct and detailed feedback.
- Request 6 was left unchanged to demonstrate this new error reporting approach.

## Additional Improvements and Code Quality Fixes

- Reorganized the original `books` package into three distinct domains: **book**, **author**, and **order**.
- Each domain now has its own package, entities, services, and controllers to better reflect business contexts and
  improve modularity.
- Moved business logic from controllers to the service layer to improve code structure and testability.
- Added and unified validation annotations on path variables and DTOs to ensure consistent and accurate data validation.
- Removed response-side validation to avoid redundancy.
- Converted obvious DTO candidates into Java records for immutability and simplicity.
- Added cascade delete to the `order_item` foreign key to ensure clean removal of dependent records.
- Implemented custom `EntityNotFoundException` classes and improved exception handling application-wide.
- Removed redundant `@ResponseBody` annotations from `AuthorController` and `BookController`.
- Unified the base entity class to reduce code duplication and enforce consistency.
- Added entity-level validation using JPA and Bean Validation annotations.
- Refined database constraints on fields such as price, quantity, and ISBN to preserve data integrity.
- Enabled creation of orders supporting multiple items in a single request (batch orders).
- Simplified mapper interfaces by:
    - Extracting separate mappers where appropriate.
    - Removing unused methods.
    - Unifying naming conventions.
    - leveraging MapStruct features.
- Removed the redundant `@Component` annotation from `WebSecurityConfig` to avoid unnecessary bean registration.
- Additional minor improvements enhancing readability, maintainability, and robustness.

## Other Considerations and Future Enhancements

Some but not all of following points are more complex tasks on its own.

I can implement these individually upon request.

I'm sure I've missed something: **this exercise is art** - remarkable how many things went purposefully wrong :)

- **Proper Security Implementation:**
    - Avoid passing `customerId` directly in `CreateOrderDto`; retrieve it from the security context instead.
    - Restrict access to sensitive endpoints to specific roles or user groups.
- **Testing:**
    - Use TestContainers for integration testing instead of H2, since H2 does not support Postgres views properly.
- **Database Migration Strategy:**
    - Separate database structure changelogs from data population changelogs to avoid loading test/mock data in
      production.
- **Better Password Encoding:**
    - Replace deprecated default encoder with BCrypt password encoder for improved security.
- **DTO Optimization:**
    - Create simplified (gutted) DTOs for list queries (e.g., fetching all authors) to reduce response payload size.
- **Email Constraints Consistency:**
    - Currently, author emails do not have uniqueness constraints while customer emails do—this should be reviewed.
- **Database Schema Refinement:**
    - Overlapping fields among `author`, `customer`, and potential `user` tables indicate the schema needs
      reconsideration and normalization.
    - Split concatenated customer name into first and last names for consistency.
- **Performance Enhancements:**
    - Add explicit indexes on foreign key columns to improve query performance.
- **Data Type Optimization:**
    - Review varchar field lengths, potentially reduce unnecessarily large sizes.
- **Migration Tool Upgrade:**
    - Consider upgrading Liquibase from version 3.8 to 4.4+ for better features and support.
- **API Enhancements:**
    - Unify API response formats, as in error responses.
    - Introduce API versioning.
- **Add detailed JavaDocs and inline comments throughout the codebase**:
- **Jackson Configuration and Dependencies:**
    - The current JacksonConfiguration customizes field visibility and date formatting globally. Consider whether this
      behavior is necessary — Spring Boot’s default configuration may be sufficient in many cases.
    - The explicit Jackson dependencies (jackson-core, jackson-databind) may be redundant, as they are already included
      transitively via spring-boot-starter-web.
      Environment Configuration:
- **Environment Configuration:**
    - Introduce separate configuration files (e.g. `application-dev.yml`, `application-test.yml`,
      `application-prod.yml`) and leverage **Spring Profiles** to better isolate environment-specific settings such as:
        - Database connection details
        - Logging levels
        - Feature toggles