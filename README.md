# Ikimina Manager – Digital Group Savings & Loan Tracking System

**Name:** Peace Bizima
**Student ID**: 27778
**course**: Webtech Wednesday group
**Project:** Ikimina Manager – Digital Group Savings & Loan Tracking System

## About the project

Ikimina Manager is a backend API for the savings circles (ibimina) that neighbours, coworkers and church members run in Rwanda. A member asks for a loan, and the treasurer of a savings group handles it. The loan goes from REQUESTED to APPROVED, and ends as DISBURSED or REJECTED. After the money is given out, the treasurer records every repayment the member makes.

## Main entities

| Entity | What it is |
|---|---|
| Member | A person in the savings circle (has a unique member number such as IKM-0017) |
| SavingsGroup | A numbered group a treasurer is assigned to |
| LoanProduct | A kind of loan (Emergency, School Fees, Business, Agriculture) |
| Treasurer | Handles loans, belongs to one savings group, can hold several loan products |
| Loan | A dated loan with a status and an amount |
| Repayment | A payment made on a DISBURSED loan |

**How to Run It**

*first Know in my pomx i used Java version25 so if you are not using 25v change **pom.xml**

### 1. Create the database

Open pgAdmin (or psql) and create an empty database called `ikimina`:

```
CREATE DATABASE ikimina;
```
### 2. Start-up the app after getting the codes from Git

On Windows:

```
.\mvnw.cmd spring-boot:run
```

Wait until the console shows `Started IkiminaManagerApplication`. The API now runs at:

```
this is my working path: http://localhost:8083
```

### 6. Test it in Postman

**import the postman collection file in postman it's called: ikimina-manager-midterm.postman_collection.json**

Run the other requests in folder order (Part A to F, then Bonus). Every request is named with its requirement ID, for example `D2 ok - requested to approved` or `D2 error - wrong status move`.

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
