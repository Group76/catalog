# Use Azul Zulu OpenJDK 22 as the base image
FROM azul/zulu-openjdk:22

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file (assuming it's named "app.jar") from the host to the container
COPY build/libs/*.jar app.jar

# Expose the port your application will listen on
EXPOSE 8080

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
