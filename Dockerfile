FROM eclipse-temurin:21-jre-alpine

WORKDIR /condinviec_consumer

# Copy bất kỳ file jar nào trong target và đổi tên thành codinviec.jar
COPY target/*.jar condinviec_consumer.jar

# Expose port Spring Boot
EXPOSE 8081

# Chạy Spring Boot
ENTRYPOINT ["java", "-jar", "condinviec_consumer.jar"]