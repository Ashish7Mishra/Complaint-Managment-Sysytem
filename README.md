Complaint Management System (CMS)
-------------------------------------


Overview
---------
The Complaint Management System (CMS) is a full-stack web application built to streamline the process of submitting, tracking, and managing customer complaints. It provides a user-friendly interface for users to file complaints and a secure admin dashboard for managing them. The system includes email notifications to keep users informed about complaint status updates.
Features

User Portal: Allows users to submit complaints, view their complaint history, and track statuses.
Admin Dashboard: Enables admins to view all complaints, update statuses, and manage user submissions.
Email Notifications: Sends automated emails to users when complaint statuses are updated, using Thymeleaf templates and JavaMailSender.
Responsive Design: Clean, grid-based layouts with modern styling, optimized for desktop and mobile devices.
Secure Access: Role-based authentication with Spring Security (ROLE_USER for users, ROLE_ADMIN for admins).
Status Management: Complaints can be marked as Open, In Progress, or Resolved with color-coded indicators.
Error Handling: Graceful handling of invalid complaints with user-friendly alerts.

Tech Stack
----------
Backend: Spring Boot 3.5.3, Spring Data JPA, Spring Security, Hibernate 6.6.18, MySQL 8.0.42
Frontend: Thymeleaf 3.1.3, HTML5, CSS3 (custom style.css with grid layouts)
Email: Spring Boot Starter Mail with JavaMailSender and Thymeleaf templates
Server: Apache Tomcat 10.1.42 (embedded)
Database Connection: HikariCP
Tools: Maven, Spring Tools Suite (Eclipse-based), JDK 21

Prerequisites
--------------
Java 21
Maven 3.6+
MySQL 8.0+
SMTP server (e.g., Gmail) for email notifications
Git

Setup Instructions
-------------------
Clone the Repository:
git clone https://github.com/yourusername/cms.git
cd cms


Configure MySQL:
----------------
Create a database named cms_db.
Update src/main/resources/application.properties:spring.datasource.url=jdbc:mysql://localhost:3306/cms_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


Build and Run:
--------------
mvn clean install
mvn spring-boot:run

The application will start at http://localhost:8080.

Database Initialization:
------------------------
The schema is auto-generated via spring.jpa.hibernate.ddl-auto=update.
Optionally, seed the database with sample data:INSERT INTO users (email, name, password, role) VALUES
('admin@example.com', 'Admin User', '{bcrypt}$2a$10$...', 'ROLE_ADMIN'),
('user@example.com', 'Test User', '{bcrypt}$2a$10$...', 'ROLE_USER');
INSERT INTO complaints (title, description, status, created_at, user_id) VALUES
('Test Complaint', 'Sample issue.', 'Open', NOW(), 2);


Email Configuration:
--------------------
Ensure a valid SMTP configuration in application.properties.
Gmail users: Generate an App Password from Google Account settings.

Usage

Access the Application:

Open http://localhost:8080 in a browser.
Log in as:
Admin: admin@example.com (ROLE_ADMIN) to access /admin/complaints and /admin/complaint/{id}.
User: user@example.com (ROLE_USER) to submit and view complaints.


Key Endpoints:

/login: User/admin login page.
/complaints: User complaint submission and history.
/admin/complaints: Admin dashboard to view all complaints.
/admin/complaint/{id}: View and update a specific complaint’s status.
Email notifications are sent automatically on status updates.


Screenshots:
------------

<img width="1920" height="931" alt="Screenshot 2025-07-18 201254" src="https://github.com/user-attachments/assets/35031e0e-1669-450e-8b1b-bf22d220785a" />
<img width="1907" height="921" alt="Screenshot 2025-07-18 200938" src="https://github.com/user-attachments/assets/fd27e1bf-6e6a-4c8e-971d-7c9cf818cf15" />
<img width="577" height="837" alt="Screenshot 2025-07-18 201206" src="https://github.com/user-attachments/assets/d6f428e2-b2ae-4eaf-8d96-0d4cef2848b6" />
<img width="1452" height="768" alt="Screenshot 2025-07-18 201453" src="https://github.com/user-attachments/assets/4a1321c4-b170-49af-bdaf-802007880ead" />
<img width="508" height="892" alt="Screenshot 2025-07-18 201133" src="https://github.com/user-attachments/assets/8ae37761-eccc-41fa-a66d-fabfe2d139a5" />
<img width="1903" height="812" alt="Screenshot 2025-07-18 201054" src="https://github.com/user-attachments/assets/5b5c780b-96ae-42a1-a806-978afd2bcdcb" />

Project Structure
------------------
cms/
├── src/main/java/com/example/cms
│   ├── controller/      # Controllers (e.g., AdminController, ComplaintController)
│   ├── entity/          # Entities (Complaint, User)
│   ├── service/         # Services (ComplaintService, UserService)
│   └── CMSApplication.java
├── src/main/resources
│   ├── templates/       # Thymeleaf templates (admin-complaint-view.html, etc.)
│   ├── static/css/      # CSS files (style.css)
│   └── application.properties
├── pom.xml              # Maven dependencies
└── README.md


Challenges and Solutions

Thymeleaf Error: Fixed SpelEvaluationException in admin-complaint-view.html by using th:if="${complaint == null or !complaint.present}" for Optional<Complaint>.
Email Integration: Configured JavaMailSender with Thymeleaf templates for dynamic email content.
Responsive Design: Used CSS grid (.complaint-details) and media queries for mobile compatibility.

Future Improvements

Add file upload for complaint attachments.
Implement complaint analytics dashboard.
Enhance email templates with richer formatting.

Contributing
Contributions are welcome! Please submit a pull request or open an issue for bugs, features, or suggestions.
License
This project is licensed under the MIT License.
Contact
For questions or feedback, reach out via your.email@example.com or open an issue on GitHub.
