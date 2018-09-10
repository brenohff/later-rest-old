FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/later-backend-*.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-Xmx512m", "-jar", "/app.jar"]