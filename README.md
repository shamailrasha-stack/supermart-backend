# Supermart Backend

Backend REST API for the **Supermart Inventory and Billing Management System**.

The backend is built using Spring Boot and provides REST APIs for product management, inventory management, stock updates, stock history, and the application's sales and billing operations. It uses MySQL for data persistence and Spring Data JPA/Hibernate for database interaction.

## Live Application

### Frontend

The frontend is deployed on Vercel:

https://supermart-frontend-gpdwt053w-shamail-rasha.vercel.app

### Backend

The Spring Boot backend is deployed on Railway:

https://supermart-backend-production.up.railway.app

The Vercel-hosted frontend communicates with the Spring Boot backend deployed on Railway.

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* REST APIs

---

## Features

### Product Management

* Create and manage products
* View product information
* Product category handling
* Product search and filtering

### Inventory Management

* View current inventory
* Update stock quantities
* Track stock changes
* View stock history for individual products

### Stock History

Stock changes are recorded and can be retrieved for a selected product.

Example endpoint:

```text
GET /api/inventory/{productId}/history
```

### Sales and Billing

* Product selection
* Cart and quantity handling
* Billing calculations
* Sale processing
* Inventory updates after a sale

---

## Project Structure

```text
src
└── main
    ├── java
    │   └── com
    │       └── supermart
    │           ├── config
    │           ├── controller
    │           ├── dto
    │           ├── entity
    │           ├── exception
    │           ├── repository
    │           ├── security
    │           └── service
    │
    └── resources
        └── application.properties
```

### Package Responsibilities

| Package      | Responsibility                                    |
| ------------ | ------------------------------------------------- |
| `controller` | Handles HTTP requests and exposes REST APIs       |
| `service`    | Contains business and application logic           |
| `repository` | Handles database operations using Spring Data JPA |
| `entity`     | Contains JPA entity classes                       |
| `dto`        | Contains Data Transfer Objects                    |
| `config`     | Application configuration                         |
| `security`   | Security-related configuration                    |
| `exception`  | Exception handling                                |

---

## Architecture

The backend follows a layered architecture:

```text
Frontend
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL Database
```

This separation keeps request handling, business logic, persistence, and data models organized independently.

---

## Database

The application uses **MySQL** as its relational database.

Spring Data JPA and Hibernate are used for object-relational mapping and database persistence.

For local development, configure the database connection in:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/supermart
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace the placeholder username and password with your local MySQL credentials.

> Database credentials should not be committed to GitHub.

---

## Running the Backend Locally

### Prerequisites

Make sure the following are installed:

* Java 21
* Maven
* MySQL
* Git

### 1. Clone the repository

```bash
git clone <YOUR_BACKEND_GITHUB_REPOSITORY_URL>
```

### 2. Open the project

Open the project in Spring Tool Suite, Eclipse, or another Java IDE.

### 3. Create the MySQL database

Create the required database in MySQL:

```sql
CREATE DATABASE supermart;
```

### 4. Configure the database

Update:

```text
src/main/resources/application.properties
```

with your local MySQL username and password.

### 5. Build the project

```bash
mvn clean install
```

### 6. Start the application

```bash
mvn spring-boot:run
```

Alternatively, run the main Spring Boot application class from the IDE.

The backend runs locally on:

```text
http://localhost:8080
```

---

## API

The backend exposes REST APIs for the major application modules.

### Inventory

Example:

```text
GET /api/inventory/{productId}/history
```

Returns the stock history associated with a specific product.

The frontend uses these APIs to display and update application data.

---

## Deployment

### Backend — Railway

The Spring Boot backend is deployed using Railway.

Production backend:

```text
https://supermart-backend-production.up.railway.app
```

The application is configured to use Railway's dynamically assigned port in the deployed environment while retaining port `8080` for local development.

### Frontend — Vercel

The React frontend is deployed using Vercel.

Production frontend:

```text
https://supermart-frontend-gpdwt053w-shamail-rasha.vercel.app
```

The frontend communicates with the deployed Railway backend through its REST APIs.

---

## Frontend Integration

The project consists of two separate applications:

```text
┌───────────────────────────────┐
│       React Frontend          │
│          Vercel               │
└───────────────┬───────────────┘
                │
                │ REST API
                ▼
┌───────────────────────────────┐
│      Spring Boot Backend      │
│          Railway              │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│          MySQL                │
└───────────────────────────────┘
```

---

## Development Tools

The project was developed using:

* Spring Tool Suite / Eclipse
* Visual Studio Code
* MySQL
* Maven
* Postman
* Git & GitHub
* Railway
* Vercel

---

## Repository

Backend source code:

```text
GitHub: shamailrasha-stack/supermart-backend
```

Frontend source code:

```text
GitHub: shamailrasha-stack/supermart-frontend
```

---

## Project Status

The Supermart application has been implemented with the backend and frontend integrated and deployed.

The backend provides the REST APIs required by the frontend for the implemented product, inventory, stock history, and sales/billing functionality.

---

## Author

**Shamail Rasha**

B.E. Electronics and Communication Engineering
