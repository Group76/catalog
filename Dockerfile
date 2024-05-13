FROM gradle:8.7-alpine as builder
WORKDIR /build
COPY . .

RUN gradle build --stacktrace
FROM openjdk:22

#COPY /build/libs/*.jar /app/app/application.jar
COPY --from=builder /build/build/libs/*.jar /app/app/application.jar

EXPOSE 8080 8081

CMD ["java", "-jar", "/app/app/application.jar"]