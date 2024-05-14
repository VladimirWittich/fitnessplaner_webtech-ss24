FROM ubuntu:latest
LABEL authors="vladimirwittich"

ENTRYPOINT ["top", "-b"]

# Installiere OpenJDK 16
RUN apt-get update && apt-get install -y openjdk-16-jdk

# Build Stage
FROM ubuntu:latest AS build

RUN apt-get update
COPY . .

RUN ./gradlew bootJar --no-daemon

# Final Stage
FROM openjdk:17-jdk-slim

EXPOSE 8080

# Kopiere die JAR-Datei aus der Build-Stage
COPY --from=build /build/libs/fitnessplaner-0.0.1-SNAPSHOT.jar app.jar

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "app.jar"]
