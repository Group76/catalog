FROM gradle:7.3-jdk17 as builder
WORKDIR /build
COPY . .

RUN gradle build --no-daemon
FROM openjdk:22

COPY /build/libs/*.jar /app/app/application.jar

EXPOSE 8080 8081

CMD ["java", "-jar", "/app/app/application.jar"]