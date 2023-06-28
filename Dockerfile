FROM openjdk:17-jdk-slim

COPY ./target/student-api-*.jar app.jar
EXPOSE 8080 8081
ENV TZ="Europe/Warsaw" \
    JAVA_OPTS="" \
    APP_OPTIONS="--server.port=8080"
ENTRYPOINT [ "sh", "-c", "java -jar app.jar" ]
