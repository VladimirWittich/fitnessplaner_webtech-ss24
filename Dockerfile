FROM ubuntu:latest
LABEL authors="vladimirwittich"

ENTRYPOINT ["top", "-b"]

FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080


COPY --from=build /build/libs/fitnessplaner-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]