---

```markdown
# Ikimina Manager – Digital Group Savings & Loan Tracking System

**Names:** Peace Bizima  
**Student ID:** 27778  
**Course:** INSY 8322 Web Technology and Internet (webtech, AUCA)  
**Lecturer:** Patrick Dushimimana  

## Overview

This project is a backend API built for "ikimina" (traditional savings groups commonly run by neighbors, coworkers, or church members in Rwanda). 

In this system, a member requests a loan, and the group's treasurer handles it. Loans move through different stages—starting from `REQUESTED`, moving to `APPROVED`, and finally ending up as `DISBURSED` or `REJECTED`. Once the money is given out, the treasurer tracks all repayments made by the member.

## Tech Stack

- Java 25
- Spring Boot (Spring Web & Spring Data JPA)
- PostgreSQL
- Maven (Maven wrapper is included, so no need to install Maven separately)
- Postman (for testing endpoints)

## Main Entities & Data Model

| Entity | Description |
|---|---|
| `Member` | A person in the savings circle (identified by a unique number like `IKM-0017`) |
| `SavingsGroup` | A numbered group assigned to a treasurer |
| `LoanProduct` | Types of loans available (e.g., Emergency, School Fees, Business, Agriculture) |
| `Treasurer` | Manages loans, belongs to one group, and can handle multiple loan products |
| `Loan` | A dated loan entry with a specific amount and status |
| `Repayment` | Payments made against a `DISBURSED` loan |

*(Note: All IDs are automatically generated UUIDs).*

---

## How to Run the Project

### 1. Prerequisites
- Make sure you have Java 25 installed (or whatever version is specified in `pom.xml`)
- PostgreSQL should be installed and running on your machine
- Git

### 2. Clone the Repository
Open your terminal and run:
```bash
git clone [https://github.com/Bpeace21/ikimina-manager-backend.git](https://github.com/Bpeace21/ikimina-manager-backend.git)
cd ikimina-manager-backend

```

### 3. Create the Database

Open pgAdmin or psql and create a blank database:

```sql
CREATE DATABASE ikimina;

```

*(You don't need to create tables manually; Hibernate handles that automatically when the app starts).*

### 4. Configure Database Credentials

Go to `src/main/resources/application.properties` and update your local PostgreSQL password:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ikimina
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

```

### 5. Run the Application

If you are on Windows, run:

```cmd
.\mvnw.cmd spring-boot:run

```

Wait until you see `Started IkiminaManagerApplication` in the console. The server will run on `http://localhost:8083`.

### 6. Testing via Postman

1. Open Postman and click **Import** to load `ikimina-manager-midterm.postman_collection.json`.
2. Make sure your Spring Boot app is running.
3. Open the **Setup** folder in Postman and run **Setup 1**, **Setup 2**, and **Setup 3** in that order to grab the IDs needed for other tests.
4. Run the rest of the requests following the folder structure (Part A through F, plus Bonus). Request names match their requirement IDs (e.g., `D2 ok - requested to approved`).

*(If your database is empty, make sure to add sample data first—4 loan products, 4 groups, 3 treasurers, 5 members, and 10 loans—using the save endpoints before running the setup requests).*

---

## API Endpoints Reference

### Generic CRUD Endpoints

Replace `{path}` in the URL with `loan-products`, `savings-groups`, `treasurers`, `members`, or `loans`.

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/{path}/save` | Create a new record |
| `GET` | `/api/{path}/all` | Get all records |
| `GET` | `/api/{path}/{id}` | Get a single record by ID |
| `PUT` | `/api/{path}/update/{id}` | Update an existing record |
| `DELETE` | `/api/{path}/delete/{id}` | Delete a record |

### Custom Endpoints & Business Logic

| ID | Method | Endpoint |
| --- | --- | --- |
| B1 | `GET` | `/api/members/by-last-name?lastName=` |
| B2 | `GET` | `/api/loans/by-status?status=` |
| B3 | `GET` | `/api/loans/between?start=&end=` |
| C1 | `GET` | `/api/treasurers/by-loan-product?name=` |
| C2 | `GET` | `/api/savings-groups/free` |
| C3 | `GET` | `/api/loan-products/unused` |
| C4 | `GET` | `/api/members/of-treasurer/{treasurerId}` |
| D2 | `PATCH` | `/api/loans/{id}/status?status=` |
| D3 | `PUT` | `/api/treasurers/{treasurerId}/loan-products/{loanProductId}` |
| D4 | `PATCH` | `/api/treasurers/{treasurerId}/savings-group?groupNumber=` |
| E1 | `POST` | `/api/repayments/save?loanId=` |
| E2 | `GET` | `/api/repayments/member/{memberNumber}` |
| E3 | `DELETE` | `/api/repayments/delete/{id}` |
| F1 | `GET` | `/api/loans/stats/by-status` |
| F2 | `PATCH` | `/api/loans/cancel-day?treasurerId=&date=` |
| Bonus | `GET` | `/api/dashboard/summary` |
| Bonus | `GET` | `/api/loans/page?page=0&size=5&sort=loanDate,desc` |

```

```
