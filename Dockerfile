FROM maven:3.9.3-eclipse-temurin-17
WORKDIR /application
COPY . .
EXPOSE 9000
CMD mvn spring-boot:run