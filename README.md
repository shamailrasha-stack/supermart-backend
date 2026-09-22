# Supermarket Management System – Backend

This is the backend of my **Supermarket Management System**, a full-stack web application developed as part of the Full Stack Developer project assessment.

The backend provides REST APIs for authentication, products, categories, inventory, stock history, sales and dashboard data.

## Features

* User authentication
* Admin and Cashier role-based access
* Product management
* Category management
* Inventory management
* Stock updates and stock history
* Billing and sales
* Invoice management
* Sales history
* Dashboard data
* Backend validation
* REST APIs

## Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Spring Security
* Spring Validation
* MySQL
* Maven
* Lombok

## Project Structure

```text
src/main/java/com/supermart/
├── config/
├── controller/
├── dto/
├── entity/
├── exception/
├── repository/
├── security/
└── service/
```

The application follows a layered backend structure where controllers handle API requests, services contain business logic, repositories handle database operations, and entities represent the database data.

## Database

The application uses **MySQL**.

The database stores information related to:

* Users
* Products
* Categories
* Inventory
* Stock history
* Sales
* Sale items

Make sure MySQL is installed and running before starting the backend locally.

## Running the Project Locally

### 1. Clone the repository

```bash
git clone https://github.com/shamailrasha-stack/supermart-backend.git
cd supermart-backend
```

### 2. Configure the database

Create a MySQL database for the application and configure the database connection in the application's environment/configuration.

Example configuration:

```text
Database URL: YOUR_DATABASE_URL
Username: YOUR_DATABASE_USERNAME
Password: YOUR_DATABASE_PASSWORD
```

Do not commit database passwords or other private credentials to GitHub.

### 3. Build the project

Using the Maven wrapper:

```bash
./mvnw clean install
```

On Windows:

```bash
mvnw.cmd clean install
```

### 4. Run the application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The backend will start on the configured application port.

## Live Backend

https://supermart-backend-production.up.railway.app

## Frontend Repository

https://github.com/shamailrasha-stack/supermart-frontend

## Live Frontend

https://supermart-frontend-gpdwt053w-shamail-rasha.vercel.app

## Important Business Rules

* SKU and barcode values must be unique.
* Inactive products cannot be sold.
* Sale quantity cannot be greater than available stock.
* Stock is reduced after a successful sale.
* Invoice numbers are unique.
* Completed sales cannot be deleted.

## API Overview

The backend exposes REST APIs for the main application modules:

* Authentication and users
* Products
* Categories
* Inventory
* Stock history
* Sales
* Dashboard

The frontend communicates with these APIs using HTTP requests through Axios.

## Environment Configuration

For local or production deployment, database credentials and other environment-specific values should be provided through environment variables or deployment configuration.

Private credentials and secrets should not be committed to the repository.

## Deployment

The backend is deployed on **Railway** and the frontend is deployed separately on **Vercel**.

The deployed backend is used by the live frontend application.

## Demo Credentials

**Admin:** admin@supermart.com / Password : admin123

**Cashier:** cashier@supermart.com /Password : cashier123

Passwords can be shared separately if required for evaluation.
