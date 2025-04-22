# Use JDK 21 image
FROM eclipse-temurin:21-jdk as build

WORKDIR /app

# Install Maven manually
RUN apt-get update && apt-get install -y maven

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Final runtime image (slimmed down)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the JAR from the builder image
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]