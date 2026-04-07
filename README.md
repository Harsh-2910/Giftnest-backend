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

## 📸 Screenshots

### Home
<img width="1904" height="926" alt="home_giftnest" src="https://github.com/user-attachments/assets/f94f7cf0-6c58-4619-9e19-9530fa60f738" />

### Products
<img width="839" height="835" alt="products_giftnest" src="https://github.com/user-attachments/assets/cb140bdf-8c80-4a63-9707-d7a92d0ba5ad" />

### Detailed Product View
<img width="1071" height="804" alt="Detail_product_view" src="https://github.com/user-attachments/assets/5a198c05-a939-4a67-b267-155372b7b563" />

### Cart
<img width="1895" height="905" alt="cart_gitnest" src="https://github.com/user-attachments/assets/bf258546-c5db-4e75-8284-a9a8be3aac1b" />

### My Orders
<img width="1614" height="846" alt="my_orders_page" src="https://github.com/user-attachments/assets/9d880702-1872-4ed7-86cb-d5299dd100e5" />

### Profile Page
<img width="1390" height="840" alt="Profile_Page" src="https://github.com/user-attachments/assets/fbf8bd24-a014-4cd7-acbf-4c48c64be94f" />

### Payment Page
<img width="1902" height="911" alt="Payment_Page" src="https://github.com/user-attachments/assets/7b94994d-eb7c-4559-8351-aca91331ec93" />

### Order Confirmation Page
<img width="1891" height="914" alt="Order_confirmation_Page" src="https://github.com/user-attachments/assets/edb1c2ff-fb5b-48dd-abf4-73ba667f0e53" />

### Admin Order Management Page
<img width="1897" height="848" alt="Admin_order_management_page" src="https://github.com/user-attachments/assets/52a90ca0-8fb1-42ff-9001-a47dbf86102c" />

### Admin Contact Messages Management
<img width="1920" height="455" alt="Admin_contact_messages" src="https://github.com/user-attachments/assets/e1cb6a0d-eea9-4dab-9998-6e91735f20da" />

### Upload Bulk Products for Admin
<img width="1787" height="907" alt="upload_bulk_products" src="https://github.com/user-attachments/assets/794bbc1a-10d5-4882-b946-82317b460fa4" />

## 🧑‍💻 Author

Harsh ( [LinkedIn](https://www.linkedin.com/in/harsh-b16719200/) )

