FROM ubuntu:latest
LABEL authors="vladimirwittich"

# Installiere curl, das zum Hinzufügen des PPA benötigt wird
RUN apt-get update && apt-get install -y curl

# Füge das AdoptOpenJDK PPA hinzu und installiere OpenJDK 16
RUN curl -sL https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add -
RUN echo "deb https://adoptopenjdk.jfrog.io/adoptopenjdk/deb focal main" | tee /etc/apt/sources.list.d/adoptopenjdk.list
RUN apt-get update && apt-get install -y adoptopenjdk-16-hotspot

# Setze die Umgebungsvariable JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/adoptopenjdk-16-hotspot-amd64

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
