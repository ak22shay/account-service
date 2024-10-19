# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk-alpine

# Copy the JAR file from the target folder into the container
COPY target/iba-account-service.jar iba-account-service.jar

# Expose the port that the Spring Boot app runs on
EXPOSE 8081

# Set the default command to run the JAR file
ENTRYPOINT ["java", "-jar", "/iba-account-service.jar"]
