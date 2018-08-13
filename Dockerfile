FROM openjdk:8-jre-alpine
ADD target/later-rest.jar later-rest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "later-rest.jar"]