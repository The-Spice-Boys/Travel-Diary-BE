# 🌍 Travel Diary API (Backend)

Welcome to the **Travel Diary** API — a backend service that powers a social travel experience! Users can create travel itineraries, post photos and notes about their trips, and discover new ideas for things to do while on holiday. ✈️🏝️🏔️

This backend is built with **Java Spring Boot** and follows Object-Oriented Programming (OOP) principles to create a scalable and secure system.

---

## 🚀 Features

- ✨ **Create and manage itineraries** for trips
- 📸 **Upload photos** and add captions to trip activities
- 📝 **Post notes** about places visited
- 🔒 **User authentication** to protect personal itineraries and private trip details
- 🌐 RESTful API design, ready for connection with frontend clients (mobile, web)

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3** (REST API framework)
- **Spring Security** (authentication & authorization)
- **JPA (Hibernate)** (for database interaction)
- **MySQL / PostgreSQL** (relational database)
- **Maven** (dependency management)
- **Cloudinary** (cloud storage & CDN for photos)

---

## 📚 Why Java & Spring Boot?

We chose **Java** and **Spring Boot** for several reasons:

- ✅ **Object-Oriented Programming (OOP)**: Java's strong OOP design helps us structure the app into modular components — models (like `User`, `Photo`, `Itinerary`), services, and repositories — making the code easier to maintain and scale.
- ✅ **Strong ecosystem**: Spring Boot provides built-in solutions for security, database access, validation, and more.
- ✅ **Type safety**: Compile-time checks in Java help catch errors early.
- ✅ **Enterprise-grade**: Java and Spring Boot are proven technologies used by major companies for high-traffic applications.

---

## ☁️ Image Uploads with Cloudinary

We use **Cloudinary** to store and serve user-uploaded photos. This offers:

- ✅ **Cloud storage** (images don’t overload our backend server)
- ✅ **Fast global delivery** through Cloudinary's CDN
- ✅ **On-the-fly image transformations** (resize, crop, optimize)

When a user uploads a photo through the `/api/photos` endpoint:
1. The backend receives the image file.
2. It uploads the image to **Cloudinary**.
3. The backend stores only the **Cloudinary URL** and metadata (caption, modified date) in our database.

---

## 🔒 Authentication & Security

We implemented **user authentication** using **Spring Security**. Here’s how it works:

- Users register and log in with their credentials.
- Authenticated users receive a **secure session** or **token** (depending on configuration).
- API endpoints that modify data (like creating itineraries or posting photos) are protected — only the logged-in user can update or delete their content.
- Public endpoints (like fetching photos for an activity) are open for exploration, supporting the social sharing aspect of the app.

This design ensures private user data stays secure while allowing sharing where appropriate.

---

## 📂 API Overview

Here are some of the core API endpoints:

| Method  | Endpoint                         | Description                    |
|---------|----------------------------------|--------------------------------|
| `POST`  | `/api/itineraries`               | Create a new itinerary         |
| `GET`   | `/api/itineraries/{id}`          | Get itinerary details          |
| `POST`  | `/api/photos`                    | Upload a photo for an activity |
| `PATCH` | `/api/photos/{photoId}`          | Update photo caption/file      |
| `GET`   | `/api/activity/{activityId}/photos` | Get photos by activity ID  |
| `POST`  | `/api/notes`                     | Add a note to a trip            |

More detailed API docs (including request/response formats) are coming soon!

---

## 🚀 Getting Started (for Developers)

1. **Clone the repo**
   ```bash
   git clone https://github.com/your-org/travel-diary-backend.git](https://github.com/The-Spice-Boys/Travel-Diary-BE.git

2. **Configure cloudinary for file uploads**
   ```bash
   @Configuration
   public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "[insert cloud name]");
        config.put("api_key", "[insert api key]");
        config.put("api_secret", "[insert api secret]");
        return new Cloudinary(config);
    };
   }
   
3. **Configure application properties file to connect to DB**
   ```bash
   spring.application.name=Travel Diary (back end)
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.servlet.multipart.max-file-size=10MB
   spring.servlet.multipart.max-request-size=10MB

4. **Test api using post man or insomnia**
   ```bash
   http://localhost:8080/api
