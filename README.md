# Service Tally

## Overview
Service Tally is a web application designed to help schools track and manage student service hours. It provides a platform for students to submit service activities and for administrators to oversee and approve these submissions across multiple schools.

## Key Features
- **Multi-Role Authentication**
  - Secure user authentication for students and administrators
  - Role-based access control
  - Custom profile management for each user type

- **Student Features**
  - Submit service event activities
  - Track service hours progress
  - Upload and manage profile photos
  - View service history and status updates

- **Administrator Features**
  - Manage multiple schools
  - Review and approve student service submissions
  - Filter and view service events by various criteria
  - Track student progress toward service hour requirements

- **Data Management**
  - Complex filtering and sorting capabilities
  - School-specific service hour requirements
  - Graduation year tracking
  - Progress tracking and status updates

# Service Tally

## Technology Stack
- **Backend**
  - Java 17
  - Spring Boot
  - Spring Security
  - JPA/Hibernate
  - MySQL Database

- **Frontend**
  - Thymeleaf templating engine
  - Vanilla HTML and CSS
  - Pure JavaScript (no frameworks)
  - Custom responsive design without external libraries

## Technical Highlights
- Implemented complex database relationships using JPA/Hibernate
- Role-based security with Spring Security
- Custom authentication success handler
- File upload functionality for profile photos
- Advanced filtering system for service events
- DTO pattern for efficient data transfer
- Custom query methods using Spring Data JPA
- Hand-crafted responsive CSS without frameworks
- Clean, semantic HTML structure
- Vanilla JavaScript for enhanced functionality

## Setup and Installation
1. Clone the repository
2. Configure MySQL database settings in `application.properties`
3. Run `mvn clean install` to build the project
4. Start the application using `mvn spring-boot:run`
5. Access the application at `http://localhost:8080`

### Database Configuration
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Application Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/coderscampus/servicetally/
│   │       ├── config/        # Security and MVC configuration
│   │       ├── controller/    # MVC controllers
│   │       ├── domain/        # Entity classes and DTOs
│   │       ├── repository/    # Data access layer
│   │       ├── service/       # Business logic
│   │       └── util/          # Utility classes
│   └── resources/
│       ├── static/           # CSS, JS, and other static files
│       └── templates/        # Thymeleaf templates
```

## Future Improvements
- Implement email notifications for status updates
- Add reporting and analytics features
- Enhance mobile responsiveness
- Add batch processing for large datasets
- Implement caching for improved performance

## Screenshots
[Include screenshots or link to demo video here]

## Contributing
This is a portfolio project demonstrating full-stack development capabilities. While it's not open for contributions, feel free to fork and use as reference.

## License
[Specify your license here]
