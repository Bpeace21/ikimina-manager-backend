# MY Project: Ikimina Manager – Digital Group Savings & Loan Tracking System

**Names:** Peace Bizima
**Student ID:** 27778
**Course:** INSY 8322 Web Technology and Internet (webtech,AUCA)
**Lecturer:** Patrick Dushimimana
**Project:** Ikimina Manager – Digital Group Savings & Loan Tracking System

## About the project

Ikimina Manager is a backend API for the savings circles (ibimina) that neighbours, coworkers and church members run in Rwanda. A member asks for a loan, and the treasurer of a savings group handles it. The loan goes from REQUESTED to APPROVED, and ends as DISBURSED or REJECTED. After the money is given out, the treasurer records every repayment the member makes.

## What it uses

- Java 25
- Spring Boot (Spring Web and Spring Data JPA)
- PostgreSQL
- Maven (the project includes the Maven wrapper, so you don't need to install Maven)
- Postman for testing

## Main entities

| Entity | What it is |
|---|---|
| Member | A person in the savings circle (has a unique member number such as IKM-0017) |
| SavingsGroup | A numbered group a treasurer is assigned to |
| LoanProduct | A kind of loan (Emergency, School Fees, Business, Agriculture) |
| Treasurer | Handles loans, belongs to one savings group, can hold several loan products |
| Loan | A dated loan with a status and an amount |
| Repayment | A payment made on a DISBURSED loan |

All ids are UUIDs, generated automatically.

## Steps to run the API

### 1. What you need first

- Java 25 (or the version in `pom.xml`)
- PostgreSQL installed and running
- Git

### 2. Get the code

```
git clone https://github.com/Bpeace21/ikimina-manager-backend.git
cd ikimina-manager-backend
```

### 3. Create the database

Open pgAdmin (or psql) and create an empty database called `ikimina`:

```
CREATE DATABASE ikimina;
```

The tables are created automatically when the app starts, so you don't create them yourself.

### 4. Set your database login

Open `src/main/resources/application.properties` and put your own PostgreSQL password:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/ikimina
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

### 5. Start the app

On Windows:

```
.\mvnw.cmd spring-boot:run
```

Wait until the console shows `Started IkiminaManagerApplication`. The API now runs at:

```
http://localhost:8083
```

### 6. Test it in Postman

1. Open Postman and click **Import**.
2. Choose the file `ikimina-manager-midterm.postman_collection.json` from this project.
3. Make sure the app from step 5 is running.
4. Open the **Setup** folder and send **Setup 1**, **Setup 2** and **Setup 3**, in that order. They collect the ids that the other requests need.
5. Run the other requests in folder order (Part A to F, then Bonus). Every request is named with its requirement ID, for example `D2 ok - requested to approved` or `D2 error - wrong status move`.

If the database is empty, first add the sample data (4 loan products, 4 savings groups, 3 treasurers, 5 members and 10 loans) using the save endpoints, then run the Setup requests.

## Main endpoints

Every entity uses the same pattern. Replace `{path}` with `loan-products`, `savings-groups`, `treasurers`, `members` or `loans`.

| Method | Path | What it does |
|---|---|---|
| POST | `/api/{path}/save` | Save a new record |
| GET | `/api/{path}/all` | List all records |
| GET | `/api/{path}/{id}` | Get one record |
| PUT | `/api/{path}/update/{id}` | Update a record |
| DELETE | `/api/{path}/delete/{id}` | Delete a record |

Other endpoints:

| ID | Method | Path |
|---|---|---|
| B1 | GET | `/api/members/by-last-name?lastName=` |
| B2 | GET | `/api/loans/by-status?status=` |
| B3 | GET | `/api/loans/between?start=&end=` |
| C1 | GET | `/api/treasurers/by-loan-product?name=` |
| C2 | GET | `/api/savings-groups/free` |
| C3 | GET | `/api/loan-products/unused` |
| C4 | GET | `/api/members/of-treasurer/{treasurerId}` |
| D2 | PATCH | `/api/loans/{id}/status?status=` |
| D3 | PUT | `/api/treasurers/{treasurerId}/loan-products/{loanProductId}` |
| D4 | PATCH | `/api/treasurers/{treasurerId}/savings-group?groupNumber=` |
| E1 | POST | `/api/repayments/save?loanId=` |
| E2 | GET | `/api/repayments/member/{memberNumber}` |
| E3 | DELETE | `/api/repayments/delete/{id}` |
| F1 | GET | `/api/loans/stats/by-status` |
| F2 | PATCH | `/api/loans/cancel-day?treasurerId=&date=` |
| Bonus | GET | `/api/dashboard/summary` |
| Bonus | GET | `/api/loans/page?page=0&size=5&sort=loanDate,desc` |
