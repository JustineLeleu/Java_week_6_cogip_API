# COGIP API Documentation

This API is built using Java, SQL, Spring Boot, and Maven.

## Endpoint documentation

| Method | Endpoint | Description | Request Body | Response Body |
| --- | --- | --- | --- | --- |
| GET | /api/companies | Returns a list of all companies | - | Array of companies |
| GET | /api/companies/{id} | Returns a specific company by ID | - | Single company object |
| POST | /api/companies | Creates a new company | `{ "name": "string", "country": "string", "tva": "string", "type": "string" }` | Created company object |
| PUT | /api/companies/{id} | Updates an existing company | `{ "name": "string", "country": "string", "tva": "string", "type": "string" }` | Updated company object |
| DELETE | /api/companies/{id} | Deletes a specific company by ID | - | - |
| GET | /api/contacts | Returns a list of all contacts | - | Array of contacts |
| GET | /api/contacts/{id} | Returns a specific contact by ID | - | Single contact object |
| POST | /api/contacts | Creates a new contact | `{ "name": "string", "email": "string", "phone": "string", "company": "string" }` | Created contact object |
| PUT | /api/contacts/{id} | Updates an existing contact | `{ "name": "string", "email": "string", "phone": "string", "company": "string" }` | Updated contact object |
| DELETE | /api/contacts/{id} | Deletes a specific contact by ID | - | - |
| GET | /api/invoices | Returns a list of all invoices | - | Array of invoices |
| GET | /api/invoices/{id} | Returns a specific invoice by ID | - | Single invoice object |
| POST | /api/invoices | Creates a new invoice | `{ "contact": "string", "company": "string", "amount": "number", "dueDate": "date" }` | Created invoice object |
| PUT | /api/invoices/{id} | Updates an existing invoice | `{ "contact": "string", "company": "string", "amount": "number", "dueDate": "date" }` | Updated invoice object |
| DELETE | /api/invoices/{id} | Deletes a specific invoice by ID | - | - |
| GET | /api/users | Returns a list of all users | - | Array of users |
| GET | /api/users/{id} | Returns a specific user by ID | - | Single user object |
| POST | /api/users | Creates a new user | `{ "username": "string", "password": "string", "email": "string", "role": "string" }` | Created user object |
| PUT | /api/users/{id} | Updates an existing user | `{ "username": "string", "password": "string", "email": "string", "role": "string" }` | Updated user object |
| DELETE | /api/users/{id} | Deletes a specific user by ID | - | - |
## Creators
- [Justine](https://github.com/JustineLeleu/)
- [Virginie](https://github.com/vdourson2/)
- [Valentin](https://github.com/Valentin-Lefort)