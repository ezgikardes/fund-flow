# Loan API

Loan API is a secure and scalable RESTful service for managing loans. It includes user authentication and role-based access control using JWT.

## Features

- **JWT Authentication**: Secure login and token-based access.
- **Role-Based Authorization**: Access restrictions for Admin and Customer roles.
- **Loan Management**:
  - Create loans (Admin only).
  - List customer loans and installments.
  - Make loan payments.
- **Swagger Integration**: API documentation and testing.

## Technologies

- **Java 17**
- **Spring Boot 3**
- **Spring Security** (JWT)
- **Swagger/OpenAPI**
- **H2 Database** (for development)
