FROM openjdk:17-jdk-alpine3.14
WORKDIR .
COPY ./target/ResourceManagerApp-0.0.1-SNAPSHOT.jar .
EXPOSE 9000
ENTRYPOINT ["java","-jar","ResourceManagerApp-0.0.1-SNAPSHOT.jar"]


