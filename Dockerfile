FROM gradle:jdk15-hotspot AS cache
WORKDIR /app
ENV GRADLE_USER_HOME /cache
COPY build.gradle settings.gradle ./
RUN gradle --no-daemon build --stacktrace
     
FROM gradle:jdk15-hotspot AS builder
WORKDIR /app
COPY --from=cache /cache /home/gradle/.gradle
COPY . .
RUN gradle --no-daemon bootJar --stacktrace
     
FROM openjdk:latest
WORKDIR /app
COPY --from=builder /app/build/libs/JsonToXmlHttpService-0.0.1-SNAPSHOT.jar JsonToXmlHttpService-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/build/resources/main/ src/main/resources/
ENV PORT 8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","./JsonToXmlHttpService-0.0.1-SNAPSHOT.jar"]

