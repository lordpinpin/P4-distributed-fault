# Stage 1: Build
FROM maven:3.8.4-openjdk-11 as build

# Set working directory in container
WORKDIR /app

# Copy pom.xml and install dependencies
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy source code and build the application
COPY src /app/src
RUN mvn clean package

# Stage 2: Run
FROM openjdk:11-jre-slim

# Set working directory for runtime
WORKDIR /app

# Copy the built .jar from build stage
COPY --from=build /app/target/course-service.jar /app/course-service.jar

# Expose port
EXPOSE 7002

# Run the app
ENTRYPOINT ["java", "-jar", "course-service.jar"]