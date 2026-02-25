# 💰 Expense Tracker

 **Live Demo:** https://expense-tracker-system-gilt.vercel.app  
 **Backend API:** https://expense-tracker-system-atl9.onrender.com  

A full-stack expense tracking system built with **Spring Boot, PostgreSQL, and JWT Authentication**.  
Implements stateless security, user-level data isolation, and production cloud deployment.

--

##  Key Features
- **Stateless Authentication:** Secure user registration and login using **JWT (JSON Web Tokens)**.
- **Personalized Data:** Multi-tenant architecture—users only see and manage their own data.
- **RESTful CRUD:** Full suite of endpoints for managing expenses (Create, Read, Update, Delete).
- **Responsive UI:** Clean, modern interface built with CSS variables and Inter typography.
- **Secure Password Handling:** Industry-standard password hashing using **BCrypt**.

## 🛠 Tech Stack

### Backend
- **Java 17 / Spring Boot**
- **Spring Security** 
- **Spring Data JPA** 
- **PostgreSQL** 
- **Lombok**

### Frontend
- **HTML**
- **CSS**
- **JavaScript** 

##  System Architecture
The application follows a decoupled architecture:
1. **Frontend:** Hosted on Vercel, communicates via HTTPS with the backend.
2. **Backend:** A Dockerized Spring Boot service running on Render.
3. **Database:** A managed PostgreSQL instance ensuring data persistence.


##  API Endpoints

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Login and receive JWT | No |
| GET | `/api/expenses` | Fetch all user expenses | **Yes** |
| POST | `/api/expenses` | Add a new expense | **Yes** |
| DELETE | `/api/expenses/{id}`| Remove an expense | **Yes** |

##  Features

- User Registration & Login  
- Create / View / Delete Expenses  
- Real-time total calculation  
- Protected REST APIs  
- Responsive and Minimal UI

##  Future Improvements

- Pagination and sorting support
- Unit & integration testing
- CI/CD pipeline integration

---
## Screenshots
<img width="350" height="450" alt="image" src="https://github.com/user-attachments/assets/b09c2b4b-26bd-403b-a737-32b685dc2432" />
----
<img width="350" height="450" alt="image" src="https://github.com/user-attachments/assets/cc364eec-e801-4838-9c80-e7d085a127fc" />

##  Running Locally

### 1. Clone Repository
- Step 1: Clone this repo - git clone https://github.com/Chellapu-Jagadeesh/expense-tracker-system.git
- Step 2: Update application.properties and configure postgres.
- Step 3: Execute mvn spring-boot:run in the backend folder or simply run the ExpenseTrackerApplication.java file.
- Step 4: Open index.html in your browser.
