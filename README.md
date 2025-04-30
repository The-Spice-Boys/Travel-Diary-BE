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

---

## 📚 Why Java & Spring Boot?

We chose **Java** and **Spring Boot** for several reasons:

- ✅ **Object-Oriented Programming (OOP)**: Java's strong OOP design helps us structure the app into modular components — models (like `User`, `Photo`, `Itinerary`), services, and repositories — making the code easier to maintain and scale.
- ✅ **Strong ecosystem**: Spring Boot provides built-in solutions for security, database access, validation, and more.
- ✅ **Type safety**: Compile-time checks in Java help catch errors early.
- ✅ **Enterprise-grade**: Java and Spring Boot are proven technologies used by major companies for high-traffic applications.

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

1. **Clone the repo**:
   ```bash
   git clone https://github.com/your-org/travel-diary-backend.git
   cd travel-diary-backend
