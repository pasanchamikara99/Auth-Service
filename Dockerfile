
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/Auth-Service-3.0.2.war /app/Auth-Service-3.0.2.war

EXPOSE 8085

CMD ["java", "-jar", "/app/Auth-Service-3.0.2.war"]
