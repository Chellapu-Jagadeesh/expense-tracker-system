# 💰 Expense Tracker Pro

A full-stack financial tracking application that allows users to create, view, and delete expenses through a RESTful API with a responsive frontend interface.

---

## 🌐 Live Demo
<img width="609" height="480" alt="image" src="https://github.com/user-attachments/assets/9d8b2fe4-7b92-48ce-9a46-9442cee90161" />

- **Frontend:** https://expense-tracker-system-gilt.vercel.app/
- **Backend API:** https://expense-tracker-system-atl9.onrender.com/api/expenses

---

##  Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Docker

### Frontend
- HTML5
- CSS3
- JavaScript (ES6 Fetch API)

### Deployment
- Render (Backend)
- Vercel (Frontend)

---

##  Architecture Overview

Frontend (Vercel)  
⬇  
REST API (Spring Boot - Render)  
⬇  
PostgreSQL Database  

The backend follows a layered architecture:
- Controller Layer (API endpoints)
- Repository Layer (Data access via JPA)
- Entity Layer (Database mapping)

---

##  Features

- RESTful API design for expense management
- Create, view, and delete expenses
- Input validation for clean data handling
- CORS configuration for secure cross-origin requests
- Persistent storage using PostgreSQL
- Dockerized backend for cloud deployment
- Responsive and minimal UI

---

##  API Endpoints

| Method | Endpoint | Description |
|--------|----------|------------|
| GET    | /api/expenses | Retrieve all expenses |
| POST   | /api/expenses | Create a new expense |
| DELETE | /api/expenses/{id} | Delete expense by ID |

---

##  Database Schema

Table: `expenses`

- id (BIGSERIAL, Primary Key)
- title (VARCHAR, NOT NULL)
- amount (DOUBLE PRECISION, NOT NULL)
- category (VARCHAR)
- date_created (TIMESTAMP)

---

##  Engineering Highlights

- Designed relational schema and mapped it using JPA annotations
- Implemented validation using Jakarta Validation API
- Used Git-based workflow with structured commits
- Containerized application using Docker for reproducible builds
- Configured environment variables for secure cloud database access

---

##  Future Improvements

- JWT-based authentication
- User-specific expense tracking
- Pagination and sorting support
- Unit & integration testing
- CI/CD pipeline integration

---

##  Running Locally

### 1. Clone Repository
```bash
git clone https://github.com/Chellapu-Jagadeesh/expense-tracker-system.git
cd expense-tracker-system/backend
