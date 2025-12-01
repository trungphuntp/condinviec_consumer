
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/target/codinviec_consumer-0.0.1-SNAPSHOT.jar codinviec_consumer.jar

# Chạy Spring Boot
ENTRYPOINT ["java", "-jar", "codinviec_consumer.jar"]
EXPOSE 8080
