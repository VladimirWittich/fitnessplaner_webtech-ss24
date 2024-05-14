FROM openjdk:17-jdk-slim AS build

WORKDIR /app

# Installiere alle erforderlichen Tools und Bibliotheken
RUN apt-get update && \
    apt-get install -y curl

# Gradle Installation
RUN curl -L https://services.gradle.org/distributions/gradle-7.3-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    rm gradle.zip && \
    mv gradle-7.3 /usr/local/gradle

ENV PATH="/usr/local/gradle/bin:${PATH}"

# Kopiere die Projektdateien
COPY . .

# Baue die Anwendung
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

# Kopiere die gebaute JAR-Datei aus dem Build-Image
COPY --from=build /app/build/libs/fitnessplaner-0.0.1-SNAPSHOT.jar app.jar

# Exponiere den Port, auf dem die Anwendung läuft
EXPOSE 8080

# Starte die Anwendung beim Ausführen des Containers
ENTRYPOINT ["java", "-jar", "app.jar"]
