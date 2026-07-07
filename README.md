<div align="center">

# 💰 Finance Management REST API

### Secure • Scalable • Production-Ready Backend built with Spring Boot

<p align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-Authentication-black?style=for-the-badge&logo=jsonwebtokens)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?style=for-the-badge&logo=hibernate)
![Swagger](https://img.shields.io/badge/Swagger-API_Docs-85EA2D?style=for-the-badge&logo=swagger)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</p>

*A secure Finance Management Backend that provides authentication, role-based authorization, transaction management, financial analytics, and RESTful APIs using Spring Boot.*

</div>

---

# 📖 Project Overview

Finance Management REST API is a secure backend application developed using **Spring Boot** that helps users manage financial transactions efficiently.

The application provides **JWT-based authentication**, **role-based authorization**, **transaction management**, and **dashboard analytics**, while following industry-standard layered architecture.

This project demonstrates modern backend development practices using Java technologies and can serve as the backend for personal finance applications.

---

# ✨ Features

### 🔐 Authentication & Security

- JWT Authentication
- Secure Login & Registration
- Password Encryption using BCrypt
- Spring Security Integration

### 👥 Role-Based Access Control

- ADMIN
- ANALYST
- VIEWER

### 💰 Financial Management

- Create Transactions
- Update Transactions
- Delete Transactions
- View Transactions
- Transaction Filtering

### 📊 Dashboard

- Financial Summary
- Income Overview
- Expense Overview

### ⚡ Backend Features

- RESTful APIs
- Global Exception Handling
- Layered Architecture
- DTO Pattern
- Validation
- Soft Delete
- MySQL Integration

---

# 🛠 Tech Stack

## Backend

- Java 17
- Spring Boot 3.2
- Spring Security
- Spring Data JPA
- Hibernate

## Database

- MySQL

## Authentication

- JWT (JSON Web Token)

## API Documentation

- Swagger / OpenAPI

## Tools

- Maven
- Git
- Postman
- IntelliJ IDEA

---

# 🏗 System Architecture

```
                Client
                   │
                   ▼
          Spring Security
                   │
            JWT Authentication
                   │
             REST Controllers
                   │
              Service Layer
                   │
          Spring Data JPA
                   │
               Hibernate
                   │
                 MySQL
```

---

# 🔒 Roles & Permissions

| Feature | ADMIN | ANALYST | VIEWER |
|----------|:-----:|:-------:|:------:|
| Register/Login | ✅ | ✅ | ✅ |
| View Transactions | ✅ | ✅ | ✅ |
| Create Transaction | ✅ | ✅ | ❌ |
| Update Transaction | ✅ | ✅ | ❌ |
| Delete Transaction | ✅ | ❌ | ❌ |
| Manage Users | ✅ | ❌ | ❌ |
| Dashboard | ✅ | ✅ | ✅ |

---

# 📡 API Endpoints

## Authentication

| Method | Endpoint |
|----------|----------|
| POST | `/api/auth/register` |
| POST | `/api/auth/login` |

---

## Transactions

| Method | Endpoint |
|----------|----------|
| GET | `/api/transactions` |
| GET | `/api/transactions/{id}` |
| POST | `/api/transactions` |
| PUT | `/api/transactions/{id}` |
| DELETE | `/api/transactions/{id}` |

---

## Dashboard

| Method | Endpoint |
|----------|----------|
| GET | `/api/dashboard/summary` |

---

## User Management

| Method | Endpoint |
|----------|----------|
| GET | `/api/users` |
| GET | `/api/users/{id}` |
| PATCH | `/api/users/{id}/toggle-status` |
| DELETE | `/api/users/{id}` |

---

# 📂 Project Structure

```
finance-backend
│
├── src
│   ├── main
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── entity
│   │   ├── dto
│   │   ├── security
│   │   ├── config
│   │   └── FinanceBackendApplication.java
│
├── docs
│
├── postman
│
├── pom.xml
│
└── README.md
```

---

# 🧠 Design Decisions

- JWT expires after **24 hours**
- BCrypt password hashing
- Soft Delete for transactions
- Monetary values stored using **BigDecimal**
- DTOs used to separate API models
- Layered architecture for maintainability
- Role assigned during user registration

---

# 📖 Swagger Documentation

Once the application is running:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger provides an interactive interface for testing every API endpoint.

---

# 📮 Postman Collection

Import the Postman Collection from:

```
/postman
```

All authentication and transaction APIs are available for testing.

---

# ⚙ Installation Guide

## Prerequisites

- Java 17
- Maven
- MySQL

### Clone Repository

```bash
git clone https://github.com/Aayush7097/finance-backend.git
```

### Navigate

```bash
cd finance-backend
```

### Create Database

```sql
CREATE DATABASE finance_db;
```

### Configure

Update

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run

```bash
mvn spring-boot:run
```

Application starts on

```
http://localhost:8080
```

---

# 🧪 Testing

The APIs can be tested using:

- ✅ Swagger UI
- ✅ Postman
- ✅ IntelliJ HTTP Client

---

# 📈 Future Enhancements

- Docker Support
- CI/CD Pipeline using GitHub Actions
- Unit & Integration Testing
- Redis Caching
- Email Verification
- Audit Logs
- Advanced Analytics
- Cloud Deployment (AWS/Azure)
- Frontend Integration

---

# 📸 Screenshots

> Add the following screenshots inside `/docs/screenshots`

- Login API
- Swagger UI
- Postman Collection
- Dashboard APIs
- Database Schema
- Project Structure

---

# 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

# 📜 License

This project is licensed under the **MIT License**.

---

# 👨‍💻 Author

## Aayush Kumar

**Java Full Stack Developer | Backend Developer | AI Enthusiast**

💻 GitHub: https://github.com/Aayush7097

---

<div align="center">

### ⭐ If you found this project helpful, don't forget to Star the repository!

</div>
