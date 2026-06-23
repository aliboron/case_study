# Airline Reservation API

A robust RESTful API built with **Spring Boot 4** for managing airline reservations. This project provides a complete backend solution for managing users, airplanes, airports, and flight bookings, backed by a PostgreSQL database and secured with JWT-based authentication.

## 🚀 Features

* **JWT Authentication & Authorization**: Secure signup and login endpoints. Role-Based Access Control (RBAC) ensures users can only access what they are permitted to (e.g., `ADMIN` vs `PASSENGER`).
* **Flight & Fleet Management**: Full CRUD endpoints for managing `Airplanes` and `Airports` (restricted to Admins).
* **Booking System**: Passengers can create reservations, view their booking history, and Admins can manage all reservations across the system.
* **Business Logic Validation**: Prevents overbooking (capacity checks) and double-booking (seat availability checks).
* **Structured Error Handling**: Global exception handling (`@RestControllerAdvice`) provides clean, standardized JSON error responses for bad requests, conflicts, and not-found resources.
* **Database Migrations**: Integrated with **Flyway** to automatically manage schema creation and seed initial data for users, airports, airplanes, and reservations.
* **Dockerized Environment**: Fully containerized setup. A multi-stage `Dockerfile` and `compose.yaml` are provided to spin up the entire application and database with a single command.
* **Mail Integration**: A basic mail integration for sending emails on reservation creation.
---

## 🛠️ Technology Stack

* **Java 25**
* **Spring Boot** (Web, Data JPA, Security, Validation)
* **PostgreSQL** (Relational Database)
* **Flyway** (Database Migrations)
* **JWT (JSON Web Tokens)** (Stateless Authentication)
* **Gradle (Kotlin DSL)** (Build Tool)
* **Docker & Docker Compose** (Containerization)
* **Redis** (Caching)
* **QueryDSL** 

---

## 🏗️ Project Structure

```text
src/main/java/com/voco/case_study/
├── advices/        # Global exception handlers (ControllerAdvice)
├── config/         # Configuration classes
├── controllers/    # REST API endpoints
├── dtos/           # Data Transfer Objects (Requests & Responses)
├── enums/          # Enumerations (Role, ReservationStatus)
├── models/         # JPA Entities (User, Airplane, Airport, Reservation)
├── repositories/   # Spring Data JPA repositories
├── security/       # JWT filters, utilities, and security config
└── services/       # Core business logic implementation
```

---

## 🐳 Running the Application

The easiest way to run the application is using Docker Compose. It will automatically start a PostgreSQL database, run Flyway migrations, build the Spring Boot app, and expose it on port `8080`.

### Prerequisites
* Docker and Docker Compose installed.

### Steps
1. Clone the repository.
2. From the root directory of the project, run:
   ```bash
   docker compose up -d --build
   ```
3. The application will be available at `http://localhost:8080`. Swagger UI is available at `http://localhost:8080/swagger-ui.html`

To view the application logs:
```bash
docker compose logs -f app
```

To stop the application:
```bash
docker compose down -v
```

---

## 📚 API Endpoints

### Authentication (`/auth`)
* `POST /auth/signup` - Register a new passenger.
* `POST /auth/signin` - Authenticate and receive a JWT.

### Airplanes (`/airplanes`)
* `GET /airplanes` - List all airplanes (Public).
* `GET /airplanes/{id}` - Get airplane details (Public).
* `POST /airplanes` - Add a new airplane (**Admin**).
* `PUT /airplanes/{id}` - Update airplane details (**Admin**).
* `DELETE /airplanes/{id}` - Remove an airplane (**Admin**).

### Airports (`/airports`)
* `GET /airports` - List all airports (Public).
* `GET /airports/{id}` - Get airport details (Public).
* `POST /airports` - Add a new airport (**Admin**).
* `PUT /airports/{id}` - Update airport details (**Admin**).
* `DELETE /airports/{id}` - Remove an airport (**Admin**).

### Reservations (`/reservations`)
* `POST /reservations` - Book a flight seat (**Passenger/Admin**).
* `GET /reservations/me` - View current user's booking history (**Passenger/Admin**).
* `GET /reservations` - View all reservations in the system (**Admin**).
* `GET /reservations/search/` - Search reservations by user or status (**Admin**).
* `DELETE /reservations/{id}` - Cancel a reservation (**Admin**).

---

## 🔐 Seeding & Default Users

When the application starts, Flyway automatically seeds the database with airports, airplanes, and multiple default users. You can log in immediately using the following credentials:

| Role | Email                  | Password   |
| :--- |:-----------------------|:-----------|
| **Admin** | `admin1@airline.com`   | `pass1234` |
| **Passenger** | `john.doe@email.com`   | `pass1234` |
| **Passenger** | `jane.smith@email.com` | `pass1234` |

*(Note: All seeded users use `pass1234` as their default password).*

---

## ⚙️ Configuration

Application properties and database credentials can be configured via environment variables inside the `compose.yaml` file, or via `src/main/resources/application.yaml`.

Key properties:
* `JWT_SECRET`: The secret key used for signing JSON Web Tokens.
* `JWT_EXPIRATION_MS`: Token lifespan.
* Database URL, Username, and Password.
* `MAIL_HOST`,`MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD` fields for mail integration. 
