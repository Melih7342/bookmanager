# 📚 BookManager API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.x-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Security](https://img.shields.io/badge/Spring%20Security-RBAC-blue)](https://spring.io/projects/spring-security)

A professional backend REST API built with **Spring Boot 4.0.2** to manage a personal book library. This project focuses on high-quality code architecture, secure authentication, and automated API documentation.

## 🌟 Key Features

* **Robust Security:** Complete authentication flow using Spring Security and BCrypt password hashing.
* **Role-Based Access Control (RBAC):** * **USER:** Can browse books and manage their own profile.
    * **ADMIN:** Authorized to perform sensitive operations like deleting book entries.
* **Public Profiles:** Anonymous access to public user profiles (reading lists) using DTOs to ensure data privacy.
* **Data Persistence:** File-based H2 database integration, ensuring data is preserved across application restarts.
* **Interactive Documentation:** Automated API specification and testing interface via Swagger UI.

## 🛠 Tech Stack

* **Framework:** Spring Boot 4.0.2
* **Security:** Spring Security (Basic Auth)
* **Persistence:** Spring Data JPA, Hibernate
* **Database:** H2 (File-based storage in `./data/bookdb`)
* **Documentation:** Springdoc OpenAPI (Swagger UI)
* **Utilities:** Lombok, Java 21

## 🚦 Getting Started

### Prerequisites
* Java 21 JDK
* IntelliJ IDEA (recommended) or any Java IDE

### Installation & Execution
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Melih7342/bookmanager.git](https://github.com/Melih7342/bookmanager.git)
    cd bookmanager
    ```
2.  **Run the application:**
    Use the Gradle wrapper to start the server:
    ```bash
    ./gradlew bootRun
    ```
3.  **Access the API:**
    The server will be running at `http://localhost:8080`.

## 🔐 Demo Credentials

The application is pre-seeded with the following test accounts:

| Role          | Username | Password | Permissions                        |
| :------------ | :------- | :------- | :--------------------------------- |
| **Administrator** | `admin`  | `admin`  | Full CRUD access to Book Table + Delete books    |
| **Standard User** | `user`   | `user`   | User specific endpoint access|

## 📖 API Documentation (Swagger)

You can explore and test all API endpoints directly through the browser:
👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

## 🗄 Database Management (H2 Console)

To inspect the database tables directly, use the H2 Console:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:file:./data/bookdb`
* **Username:** `sa`
* **Password:** *(leave empty)*

## 📂 Project Structure

* `config/` - Security, OpenAPI, and Data Initialization settings.
* `controller/` - REST API Endpoints (User, Books).
* `exception/` - Domain specific exceptions and Global Exception Handler.
* `service/` - Business logic and UserDetailsService.
* `model/` - JPA Entities
* `utils/` - DTOs (Data Transfer Objects).
* `repository/` - Spring Data JPA Repository interfaces.

---
*Developed by Melih - Portfolio Project 2026*
