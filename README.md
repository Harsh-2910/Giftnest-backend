# 🎁 Giftnest Backend

Giftnest Backend is a robust and scalable **Spring Boot-based REST API** powering the Giftnest eCommerce platform. It handles core business logic including product management, authentication, order processing, payments, and admin operations.

This backend is designed with a focus on **clean architecture, scalability, and real-world eCommerce workflows**.

---

## 🚀 Features

### 👤 User Features
- 🔐 Secure authentication & authorization (JWT-based)
- 🛍️ Browse and search products
- 🛒 Cart management
- 📦 Order placement and management
- 💳 Payment integration using Stripe
- 👤 Profile management

### 🛠️ Admin Features
- 📦 Bulk product upload (CSV support)
- ✏️ Add, update, delete products
- 📊 Order management (confirm/cancel orders)
- 💬 Manage contact messages
- 📈 Scalable product & inventory handling

---

## 🧠 Architecture Highlights

- Layered architecture (Controller → Service → Repository)
- DTO-based request/response handling
- Exception handling with global handlers
- RESTful API design principles
- Scalable and maintainable code structure

---

## 🛠️ Tech Stack

- **Backend Framework:** Spring Boot
- **Language:** Java
- **Database:** PostgreSQL
- **ORM:** Hibernate / JPA
- **Security:** Spring Security + JWT
- **Payment Gateway:** Stripe
- **Build Tool:** Maven

---

## 📂 Project Structure

```bash
src/main/java/
│── config/         # CaffineCache, Stripe configurations
│── constants/      # Application constants
│── controller/     # REST controllers (API endpoints)
│── service/        # Business logic layer
│── repository/     # Database access layer (JPA)
│── dto/            # Data Transfer Objects
│── entity/         # Database entities
│── security/       # Security, public/protected paths & app configuration
│── exception/      # Global exception handling
│── util/           # Utility classes
```
## ⚙️ Setup & Installation
### 1️⃣ Clone the repository
```bash
git clone https://github.com/Harsh-2910/Giftnest-backend.git
cd Giftnest-backend
```

### 2️⃣ Configure environment variables

Update application.properties or application.yml:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/giftnest
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY

stripe.api.key=YOUR_STRIPE_SECRET_KEY

```

### 3️⃣ Run the application
```bash
mvn spring-boot:run
```
## 🔐 Security
- JWT-based authentication
- Role-based access control (User/Admin)
- Protected API routes
- Secure password hashing

## 🔗 Frontend Repository
👉 https://github.com/Harsh-2910/Giftnest-ui
- Make sure the frontend is configured with the correct backend URL.



