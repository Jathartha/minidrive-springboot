# Mini Drive – Spring Boot File Management System

A backend-focused Mini Drive application built using Spring Boot to understand real-world backend engineering concepts.

## Features
- File upload (multipart)
- File download
- File delete
- File rename
- List stored files
- Timestamp tracking
- RESTful API design
- Simple UI using HTML + JavaScript fetch

## Tech Stack
- Java 17
- Spring Boot
- REST APIs
- HTML + JavaScript
- Maven

## API Endpoints
- POST `/api/upload`
- GET `/api/files`
- GET `/api/download/{filename}`
- DELETE `/api/delete/{filename}`
- PUT `/api/rename`

## Run Locally

1. Clone the repository
2. Open in IntelliJ / any IDE
3. Make sure Java 17+ is installed
4. Run `MinidriveApplication`
5. Application starts on port **8081**
6. Open browser at:
   http://localhost:8081/

## Why this project?
Built to gain hands-on experience with backend API design, file handling in Spring Boot.
