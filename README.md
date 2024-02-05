# COGIP API Documentation

This API is built using Java, MySQL, Spring, and Maven.

## All dependencies

This API use the following dependencies: Junit, Spring Boot, Spring Security, Spring Data JPA, MySQL Driver, Lombok, and Spring Web, Jakarta Validation API, and Hibernate Validator.

## Endpoint documentation

| Method | Endpoint            | Description                      | Request Body                                                                          | Response Body                                | Authorization          |
|--------|---------------------|----------------------------------|---------------------------------------------------------------------------------------|----------------------------------------------|------------------------|
| POST   | /login              | User login                       | `{ "username": "string", "password": "string" }`                                      | `{ "userInfos": "user", "token": "string" }` | -                      |
| GET    | /api/companies      | Returns a list of all companies  | -                                                                                     | Array of companies                           | USER, MODERATOR, ADMIN |
| GET    | /api/companies/{id} | Returns a specific company by ID | -                                                                                     | Single company object                        | USER, MODERATOR, ADMIN |
| POST   | /api/companies      | Creates a new company            | `{ "name": "string", "country": "string", "tva": "string", "type": "string" }`        | -                                            | MODERATOR, ADMIN       |
| PUT    | /api/companies/{id} | Updates an existing company      | `{ "name": "string", "country": "string", "tva": "string", "type": "string" }`        | -                                            | ADMIN                  |
| DELETE | /api/companies/{id} | Deletes a specific company by ID | -                                                                                     | -                                            | ADMIN                  |
| GET    | /api/contacts       | Returns a list of all contacts   | -                                                                                     | Array of contacts                            | USER, MODERATOR, ADMIN |
| GET    | /api/contacts/{id}  | Returns a specific contact by ID | -                                                                                     | Single contact object                        | USER, MODERATOR, ADMIN |
| POST   | /api/contacts       | Creates a new contact            | `{ "name": "string", "email": "string", "phone": "string", "company": "string" }`     | -                                            | MODERATOR, ADMIN       |
| PUT    | /api/contacts/{id}  | Updates an existing contact      | `{ "name": "string", "email": "string", "phone": "string", "company": "string" }`     | -                                            | ADMIN                  |
| DELETE | /api/contacts/{id}  | Deletes a specific contact by ID | -                                                                                     | -                                            | ADMIN                  |
| GET    | /api/invoices       | Returns a list of all invoices   | -                                                                                     | Array of invoices                            | USER, MODERATOR, ADMIN |
| GET    | /api/invoices/{id}  | Returns a specific invoice by ID | -                                                                                     | Single invoice object                        | USER, MODERATOR, ADMIN |
| POST   | /api/invoices       | Creates a new invoice            | `{ "contact": "string", "company": "string", "amount": "number", "dueDate": "date" }` | -                                            | MODERATOR, ADMIN       |
| PUT    | /api/invoices/{id}  | Updates an existing invoice      | `{ "contact": "string", "company": "string", "amount": "number", "dueDate": "date" }` | -                                            | ADMIN                  |
| DELETE | /api/invoices/{id}  | Deletes a specific invoice by ID | -                                                                                     | -                                            | ADMIN                  |
| GET    | /api/users          | Returns a list of all users      | -                                                                                     | Array of users                               | USER, MODERATOR, ADMIN |
| GET    | /api/users/{id}     | Returns a specific user by ID    | -                                                                                     | Single user object                           | USER, MODERATOR, ADMIN |
| POST   | /api/users          | Creates a new user               | `{ "username": "string", "password": "string" }`                                      | -                                            | -                      |
| POST   | /api/users          | Creates a new user with role     | `{ "username": "string", "password": "string", "role": "string" }`                    | -                                            | ADMIN                  |
| PUT    | /api/users/{id}     | Updates an existing user         | `{ "username": "string", "password": "string", "email": "string", "role": "string" }` | -                                            | ADMIN                  |
| DELETE | /api/users/{id}     | Deletes a specific user by ID    | -                                                                                     | -                                            | ADMIN                  |

## Creators
- [Justine](https://github.com/JustineLeleu/)
- [Virginie](https://github.com/vdourson2/)
- [Valentin](https://github.com/Valentin-Lefort)