📄 Document Management Application (Spring Boot + React)

A lightweight document management system that allows users to upload, list, download, and delete PDF files.
Built with Spring Boot, MySQL, and a React frontend.

<br> <br>

🚀 Features

<br>
Upload PDF documents

Retrieve & download files by ID

List all uploaded documents

Delete documents

Stores metadata in MySQL

CORS-enabled for React frontend

<br><br>
🛠 Tech Stack
<br>
Backend: Spring Boot, Spring Web, Spring Data JPA, MySQL
Frontend: React + Vite, Axios
<br> <br>
📌 API Endpoints
<br>
Method	Endpoint	Description
POST	/documents/upload	Upload a PDF file
GET	/documents	List all documents
GET	/documents/{id}	Download a document
DELETE	/documents/{id}	Delete a document
⚙️ Configuration (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/docdb
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
app.upload.dir=uploads
<br><br>
▶️ How to Run
<br>
Backend
mvn spring-boot:run


Runs at → http://localhost:8080
<br>
Frontend
<br>
npm install
npm run dev


Runs at → http://localhost:5173

📂 File Handling Flow

React uploads file → /documents/upload

File stored on server & metadata saved in DB

Document listed with ID

User can download/delete

✨ Future Enhancements

JWT authentication

Cloud storage (AWS S3)

Document preview UI

👤 Author

Ritesh Kumar
Java Full Stack Developer
