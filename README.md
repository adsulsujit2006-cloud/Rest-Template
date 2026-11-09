# Rest-Template – Spring Boot Microservices

A Java Spring Boot microservices project demonstrating communication between multiple services using **RestTemplate**.

## 📌 Project Overview

This project contains three independent microservices:

1. **BookMyShow Services** – Handles user registration, login, and OTP verification.
2. **Movie Services** – Manages movie-related operations.
3. **Notification Services** – Handles email notifications, including OTP delivery.

The BookMyShow service communicates with the Notification service using Spring Boot's `RestTemplate` to send OTP details for email verification.

## 🏗️ Project Architecture

```text
Rest-Template
│
├── bookmyshow-services
│   ├── User Registration
│   ├── User Login
│   └── OTP Verification
│
├── movie-services
│   └── Movie Management
│
├── notification-services
│   └── Email Notifications
│
├── .gitignore
└── pom.xml
```

## 🚀 Features

- User registration and login.
- OTP generation and email verification.
- Sending OTP through the Notification Service.
- Inter-service communication using RestTemplate.
- Separate microservices for different responsibilities.
- REST API-based communication.

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- RestTemplate
- Maven
- REST APIs
- Git and GitHub

## 🔄 Microservices Communication

The project uses `RestTemplate` to communicate between microservices.

**Example workflow:**

1. User enters an email address.
2. BookMyShow Service generates an OTP.
3. BookMyShow Service sends an HTTP POST request using RestTemplate.
4. Notification Service receives the request.
5. Notification Service sends the OTP to the user's email address.

## 📂 Repository Structure

| Service | Responsibility |
|---|---|
| `bookmyshow-services` | User registration, login and OTP verification |
| `movie-services` | Movie-related functionality |
| `notification-services` | Email notification functionality |

## ⚙️ How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/adsulsujit2006-cloud/Rest-Template.git
```

2. Open the project in Spring Tool Suite, Eclipse, or IntelliJ IDEA.

3. Configure your MySQL database settings in each service's `application.properties` file, where required.

4. Update service URLs according to your local configuration.

5. Start the microservices individually.

6. Test the REST APIs using Postman.

## 👨‍💻 Author

**Sujit Adsul**

GitHub: [adsulsujit2006-cloud](https://github.com/adsulsujit2006-cloud)

---

⭐ If you find this project useful, consider giving it a star!
