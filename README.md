# Emoji Translation Game - Spring Boot Backend

A comprehensive Spring Boot REST API application for an emoji translation game with authentication, game management, and hint features.

## 📋 Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Testing](#testing)

## ✨ Features

### Module 1: Emoji Translation Game
- Three game categories: Places, Famous Personalities, Food
- 10 questions per game session
- Real-time scoring system
- Difficulty levels (Easy, Medium, Hard)
- Alternative answer support
- Game history tracking
- Progressive score accumulation

### Module 2: Hints System
- Search emojis by keyword
- Intelligent matching algorithm
- Match score ranking
- Multiple match results
- Category-based filtering

### Security Features
- JWT-based authentication
- Password encryption using BCrypt
- Secure endpoint protection
- Token-based session management
- Role-based access control ready

### Additional Features
- Input validation
- Global exception handling
- CORS configuration
- Transaction management
- Comprehensive error messages

## 🛠️ Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **Spring Security**: JWT authentication
- **Spring Data JPA**: Database operations
- **MySQL**: Relational database
- **Maven**: Dependency management
- **Lombok**: Boilerplate code reduction
- **JSON Web Token (JWT)**: Authentication tokens

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java -version
   ```

2. **Apache Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **MySQL Server 8.0+**
   ```bash
   mysql --version
   ```

4. **Postman** (for API testing)
   - Download from: https://www.postman.com/downloads/

5. **IDE** (recommended)
   - IntelliJ IDEA
   - Eclipse
   - VS Code with Java extensions

## 📥 Installation

### Step 1: Clone or Create Project Structure

Create the project directory structure as shown in the file structure section.

### Step 2: Set Up MySQL Database

1. Start MySQL server
2. Create database:
```sql
CREATE DATABASE emoji_translation_db;
```

3. Create MySQL user (optional):
```sql
CREATE USER 'emoji_user'@'localhost' IDENTIFIED BY 'emoji_password';
GRANT ALL PRIVILEGES ON emoji_translation_db.* TO 'emoji_user'@'localhost';
FLUSH PRIVILEGES;
```

### Step 3: Configure Application

Edit `src/main/resources/application.properties`:

```properties
# Update these with your MySQL credentials
spring.datasource.url=jdbc:mysql://localhost:3306/emoji_translation_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password

# JWT Secret (Change this in production!)
jwt.secret=yourVerySecretKeyThatShouldBeAtLeast256BitsLongForHS256Algorithm
```

### Step 4: Add All Source Files

Copy all the provided Java files into their respective packages:

```
src/main/java/com/emoji/translation/
├── EmojiTranslationApplication.java
├── config/
│   ├── SecurityConfig.java
│   └── WebConfig.java
├── controller/
│   ├── AuthController.java
│   ├── GameController.java
│   └── HintController.java
├── dto/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── RegisterRequest.java
│   ├── GameQuestionResponse.java
│   ├── GameAnswerRequest.java
│   ├── GameResultResponse.java
│   ├── HintRequest.java
│   └── HintResponse.java
├── entity/
│   ├── User.java
│   ├── EmojiMapping.java
│   ├── GameSession.java
│   └── GameAttempt.java
├── enums/
│   ├── GameCategory.java
│   └── DifficultyLevel.java
├── repository/
│   ├── UserRepository.java
│   ├── EmojiMappingRepository.java
│   ├── GameSessionRepository.java
│   └── GameAttemptRepository.java
├── security/
│   ├── JwtAuthenticationFilter.java
│   ├── JwtTokenProvider.java
│   └── UserDetailsServiceImpl.java
├── service/
│   ├── AuthService.java
│   ├── GameService.java
│   ├── HintService.java
│   └── EmojiMappingService.java
└── exception/
    ├── GlobalExceptionHandler.java
    ├── ResourceNotFoundException.java
    └── InvalidCredentialsException.java
```

### Step 5: Add Resources

Copy these files to `src/main/resources/`:
- `application.properties`
- `data.sql`

## 🚀 Running the Application

### Option 1: Using Maven

```bash
# Clean and install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

### Option 2: Using IDE

1. Import the project as a Maven project
2. Wait for dependencies to download
3. Run `EmojiTranslationApplication.java` as a Java Application

### Option 3: Using JAR

```bash
# Build JAR file
mvn clean package

# Run JAR
java -jar target/emoji-translation-app-1.0.0.jar
```

### Verify Application is Running

You should see output like:
```
Started EmojiTranslationApplication in X.XXX seconds
```

Application will be available at: `http://localhost:8080`

## ⚙️ Configuration

### Port Configuration
Default port is 8080. To change:
```properties
server.port=9090
```

### JWT Configuration
- **Token Expiration**: Default 24 hours (86400000 ms)
- **Secret Key**: Change in production for security

```properties
jwt.expiration=86400000  # 24 hours in milliseconds
```

### Database Configuration
The application uses `spring.jpa.hibernate.ddl-auto=update` which:
- Creates tables if they don't exist
- Updates schema when entities change
- **For production**: Change to `validate` or `none`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /auth/register | Register new user | No |
| POST | /auth/login | Login user | No |
| GET | /auth/profile | Get current user | Yes |

### Game Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | /game/categories | Get all categories | Yes |
| POST | /game/start | Start new game | Yes |
| GET | /game/next-question | Get next question | Yes |
| POST | /game/submit-answer | Submit answer | Yes |
| GET | /game/hint | Get hint for question | Yes |
| GET | /game/history | Get user's game history | Yes |

### Hint Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /hints/search | Search emojis | Yes |
| GET | /hints/search | Search emojis (query param) | Yes |

For detailed API documentation with request/response examples, see the **Postman Testing Guide**.

## 🗄️ Database Schema

### Tables Created Automatically

1. **users**
   - User authentication and profile data
   - Score tracking

2. **emoji_mappings**
   - Emoji to answer mappings
   - Categories, hints, points
   - Pre-loaded with 45 sample entries

3. **game_sessions**
   - Game session tracking
   - Category, score, completion status

4. **game_attempts**
   - Individual question attempts
   - User answers, correctness, points

### Sample Data
The application automatically loads 45 emoji mappings:
- 15 Places
- 15 Famous Personalities
- 15 Food items

## 🔐 Security

### Authentication Flow
1. User registers or logs in
2. Server generates JWT token
3. Client includes token in Authorization header
4. Server validates token for protected endpoints

### Authorization Header Format
```
Authorization: Bearer <your_jwt_token>
```

### Password Security
- Passwords are encrypted using BCrypt
- Never stored in plain text
- 10 rounds of hashing (default)

### Protected Endpoints
All endpoints except `/api/auth/**` require authentication.

## 🧪 Testing

### Using Postman

1. **Import Collection**
   - Use the Postman Testing Guide
   - Set up environment variables

2. **Test Flow**
   ```
   Register → Login → Get Token → Test Protected Endpoints
   ```

3. **Environment Variables**
   - `base_url`: http://localhost:8080
   - `token`: (auto-set after login)
   - `sessionId`: (auto-set after game start)

### Manual Testing Steps

1. **Register a User**
   ```bash
   curl -X POST http://localhost:8080/api/auth/register \
   -H "Content-Type: application/json" \
   -d '{
     "username": "testuser",
     "email": "test@example.com",
     "password": "password123",
     "fullName": "Test User"
   }'
   ```

2. **Start a Game**
   ```bash
   curl -X POST "http://localhost:8080/api/game/start?category=PLACES" \
   -H "Authorization: Bearer YOUR_TOKEN"
   ```

3. **Submit Answer**
   ```bash
   curl -X POST http://localhost:8080/api/game/submit-answer \
   -H "Authorization: Bearer YOUR_TOKEN" \
   -H "Content-Type: application/json" \
   -d '{
     "sessionId": 1,
     "questionId": 1,
     "answer": "Eiffel Tower",
     "hintUsed": false
   }'
   ```

## 🐛 Troubleshooting

### Common Issues

1. **"Access Denied" on all endpoints**
   - Check if token is included in Authorization header
   - Verify token format: `Bearer <token>`

2. **Database connection failed**
   - Verify MySQL is running
   - Check credentials in application.properties
   - Ensure database exists

3. **Port already in use**
   - Change port in application.properties
   - Kill process using port 8080

4. **JWT signature error**
   - Token might be expired (24 hours default)
   - Login again to get new token

5. **Data.sql not loading**
   - Check `spring.jpa.defer-datasource-initialization=true`
   - Verify SQL syntax in data.sql

### Enable Debug Logging

Add to `application.properties`:
```properties
logging.level.org.springframework.security=DEBUG
logging.level.com.emoji.translation=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

## 📝 Project Evaluation Criteria

This project demonstrates:

✅ **REST API**: 11 fully functional REST endpoints  
✅ **Security**: JWT-based authentication with Spring Security  
✅ **Database**: MySQL with JPA/Hibernate (relational)  
✅ **Enterprise Features**:
- Multiple modules (Auth, Game, Hints)
- Exception handling
- Validation
- Transaction management
- Layered architecture (Controller → Service → Repository)

✅ **Secure Endpoints**: All game and hint endpoints protected  
✅ **Best Practices**:
- DTO pattern
- Service layer separation
- Repository pattern
- Global exception handling

## 📄 License

This project is created for educational purposes as part of a Spring Boot evaluation class.

## 👥 Support

For issues or questions:
1. Check the troubleshooting section
2. Review Postman Testing Guide
3. Check application logs
4. Verify all configurations

## 🎯 Future Enhancements (Optional)

- Leaderboard system
- Multiplayer mode
- Time-based challenges
- Achievement badges
- Admin panel for managing emojis
- Email verification
- Password reset functionality
- Rate limiting
- API documentation with Swagger/OpenAPI