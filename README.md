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
2. Open in IntelliJ or any IDE
3. Make sure Java 17+ is installed
4. Run `MinidriveApplication` file
5. Application starts on port **8081**
6. Open browser at:
   http://localhost:8081/

## Deployment Note

This project uses local filesystem storage for uploaded files.
In a production setup, this can be replaced with cloud storage
such as AWS S3 with minimal changes.

   
## Why this project?
Built to gain hands-on experience with backend API design, file handling in Spring Boot.
