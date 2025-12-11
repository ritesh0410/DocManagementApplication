📄 Document Management Application

A full-stack Spring Boot + React application to upload, list, download, and delete PDF documents.
This backend exposes REST APIs for document storage and retrieval.

<br> <br>

🚀 Features

Upload PDF documents

Store file metadata in MySQL

Save files to server storage

List all uploaded documents

Download documents by ID

Delete documents safely

CORS enabled for React frontend

Clean REST API architecture

<br> <br>

🛠️ Tech Stack
Backend (Spring Boot)

Spring Boot

Spring Web

Spring Data JPA

MySQL

Lombok

Multipart File Handling

Frontend

React + Axios

Vite (Dev Server)
<br>

📁 Project Structure (Backend)
<br> <br>
src/main/java/com/IndraSoftech/DocManagementApplication
│
├── controller
│     └── DocumentController.java
│
├── service
│     └── DocumentService.java
│
├── dto
│     └── DocumentDto.java
│
└── entity
      └── Document.java
