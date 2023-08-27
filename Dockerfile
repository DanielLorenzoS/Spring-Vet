# Use an official OpenJDK runtime as the base image
FROM adoptopenjdk/openjdk17:latest

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the target directory of your Maven project
COPY target/Veterinaria-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
