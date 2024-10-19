# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target folder into the container
COPY target/${artifactId}-${version}.jar /app/${artifactId}-${version}.jar

# Expose the port that the Spring Boot app runs on
EXPOSE 8081

# Set the default command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/${artifactId}-${version}.jar"]
