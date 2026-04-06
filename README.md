# 💼 Finance Backend API

A scalable and secure backend system for financial data management, built using Spring Boot with JWT-based authentication and role-based access control.

---

## 📌 Project Summary

The Finance Backend API provides a robust foundation for financial applications with:

- Secure authentication and authorization
- Role-based access control
- Transaction management with filtering
- Dashboard insights for financial data

---

## 🏗 Tech Stack

- Java 17  
- Spring Boot 3.2  
- Spring Security + JWT  
- Spring Data JPA  
- MySQL  
- Maven  

---

## 🔐 Roles & Permissions

| Action              | ADMIN | ANALYST | VIEWER |
|---------------------|-------|---------|--------|
| Register/Login      | ✅    | ✅      | ✅     |
| View Transactions   | ✅    | ✅      | ✅     |
| Create Transaction  | ✅    | ✅      | ❌     |
| Update Transaction  | ✅    | ✅      | ❌     |
| Delete Transaction  | ✅    | ❌      | ❌     |
| Manage Users        | ✅    | ❌      | ❌     |
| View Dashboard      | ✅    | ✅      | ✅     |

---

## 📡 API Endpoints

### Authentication
POST /api/auth/register  
POST /api/auth/login  

### Transactions
GET    /api/transactions  
GET    /api/transactions/{id}  
POST   /api/transactions  
PUT    /api/transactions/{id}  
DELETE /api/transactions/{id}  

### Dashboard
GET /api/dashboard/summary  

### Users (Admin Only)
GET    /api/users  
GET    /api/users/{id}  
PATCH  /api/users/{id}/toggle-status  
DELETE /api/users/{id}  

---

## ⚙️ Setup Instructions

### Prerequisites
- Java 17  
- Maven  
- MySQL  

### Steps

1. Create database:
   CREATE DATABASE finance_db;

2. Configure application.properties:

   spring.datasource.url=jdbc:mysql://localhost:3306/finance_db  
   spring.datasource.username=YOUR_USERNAME  
   spring.datasource.password=YOUR_PASSWORD  

3. Run the application:
   mvn spring-boot:run  

4. Server URL:
   http://localhost:8080  

---

## 🧠 Design Decisions

- Soft delete is implemented for transactions  
- JWT tokens expire after 24 hours  
- Roles are assigned at registration  
- Monetary values use BigDecimal for precision  

---

## 📁 Project Structure

controller/  
service/  
repository/  
model/  
dto/  
security/  
config/  

---

## 🧪 Testing

Test APIs using:
- Postman  
- Swagger (if enabled)  

---

## 🚀 Future Enhancements

- Swagger/OpenAPI documentation  
- Advanced analytics  
- Notification system  
- Frontend integration  

---

## 📜 License

MIT License  

---

## ⭐ Support

If you found this project useful, please give it a star ⭐
