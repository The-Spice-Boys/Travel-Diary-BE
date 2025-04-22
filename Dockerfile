# Use a Maven base image to build the app
FROM maven:3.8.6-openjdk-21-slim as build

WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the JAR file (this will put it in target/)
RUN mvn clean package -DskipTests

# Now we’ll use a smaller JDK 21 image to run the app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the built JAR from the previous build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]