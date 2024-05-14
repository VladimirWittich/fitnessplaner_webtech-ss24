FROM ubuntu:latest
LABEL authors="vladimirwittich"

ENTRYPOINT ["top", "-b"]

FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-16-jdk
COPY . .

RUN ls -la  # Ausgabe hinzufügen, um Verzeichnisinhalt zu überprüfen

RUN ./gradlew bootJar --no-daemon
RUN echo "Build successful"  # Ausgabe hinzufügen, um den erfolgreichen Abschluss des Builds anzuzeigen

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/fitnessplaner-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
