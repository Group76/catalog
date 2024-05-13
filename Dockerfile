FROM gradle:7.3-jdk17 as builder
WORKDIR /build
COPY . .

RUN gradle build --no-daemon
FROM azul/zulu-openjdk:22

COPY /build/libs/catalog-api-0.0.1-SNAPSHOT.jar /app/app/application.jar

EXPOSE 8080 8081

#CMD ["java -jar /app/app/application.jar"]
CMD ["java", "-jar", "/app/app/application.jar"]