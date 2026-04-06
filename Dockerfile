FROM eclipse-temurin:24.0.1_9-jdk-alpine

WORKDIR /app

COPY target/giftnest-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]