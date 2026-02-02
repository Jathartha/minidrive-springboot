# Mini Drive – Secure File Management System (Spring Boot)

Mini Drive is a backend-focused file storage application built using Spring Boot.  
It provides authentication, session-based access control, and basic file management operations.

The project was developed incrementally to demonstrate real-world backend concepts such as REST APIs, authentication,  and secure file handling.

---

## Features

### Authentication & Session Management
- User registration & login
- Passwords stored securely using BCrypt hashing
- Session-based authentication using HttpSession
- Logout functionality with session invalidation
- Protected file operations (only authenticated users can access)

### File Management
- Upload files
- List uploaded files
- Download files
- Delete files
- Rename files

### UI
- Simple HTML-based UI served directly from controllers
- Authentication page (login & register)

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 In-Memory Database
- BCrypt Password Encoder
- Maven

---

## Authentication Flow

1. User registers via the authentication page
2. Password is encrypted using BCrypt before storing in the database
3. User logs in using valid credentials
4. On successful login:
   - User identity is stored in HttpSession
   - User is redirected to the file management page
5. All file-related endpoints are protected and require an active session
6. Logout invalidates the session and redirects back to the authentication page


---

## How to Run Locally

1. Clone the repository
2. Open the project in IntelliJ IDEA or any other IDE
3. Run `MiniDriveApplication` file


---

## Database

- Uses H2 in-memory database
(Database configuration is defined in `application.properties`)

---

## Design Decisions

- Session-based authentication was chosen for simplicity and clarity
- BCrypt is used to ensure secure password storage
- HTML is served directly from controllers to avoid frontend framework complexity
- File storage is handled locally for ease of development and testing

---

## Future Enhancements

- User-isolated file storage (each user sees only their own files)
- JWT-based authentication
- Improved UI ( Used only HTML as of now)

---

## Why This Project?

This project demonstrates:
- Practical use of Spring Boot for backend development
- Authentication and session handling
- Secure password management
- REST API design
- Incremental feature development



