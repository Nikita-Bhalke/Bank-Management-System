🏦 Bank Management System

A RESTful Bank Management System built using Spring Boot.
This project allows management of Banks, Accounts, and Addresses with proper validation and business rules.

🚀 Features
🔹 Bank APIs

Create Bank

Get All Banks

Get Bank by ID

Get Bank by Name

Update Bank

Delete Bank (only if total balance = 0)

Get Total Balance of a Bank

🔹 Account APIs

Create Account (Bank must exist)

Get All Accounts

Get Account by ID

Update Account

Delete Account

Get Accounts by Bank ID

Deposit Money (amount > 0)

Withdraw Money (amount > 0 & sufficient balance)

Get Account Balance

🔹 Address APIs

Create Address

Get Address by ID

Update Address

Delete Address

✅ Business Rules & Validations

Account number must be unique.

Bank must exist before creating an account.

Withdrawal:

Amount must be greater than 0.

Cannot withdraw more than available balance.

Negative balance is not allowed.

Deposit amount must be greater than 0.

Bank cannot be deleted if total account balance is greater than 0.

PIN code must be exactly 6 digits.

🛠️ Technologies Used

Java

Spring Boot

Spring Data JPA

Hibernate

MySQL

Maven

REST API
