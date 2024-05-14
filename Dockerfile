# Verwende ein OpenJDK-21-Image als Basis für den Build-Container
FROM openjdk:21 AS build

# Setze das Arbeitsverzeichnis im Build-Container
WORKDIR /app/build

# Kopiere die Projektdateien in das Arbeitsverzeichnis des Build-Containers
COPY . .

# Führe den Build der Anwendung durch
RUN ./gradlew build --no-daemon

# Erstelle ein neues Image für die Ausführung der Anwendung
FROM openjdk:21

# Setze das Arbeitsverzeichnis im Ausführungs-Container
WORKDIR /app

# Kopiere die gebaute JAR-Datei aus dem Build-Container in das Arbeitsverzeichnis des Ausführungs-Containers
COPY --from=build /app/build/libs/fitnessplaner-0.0.1-SNAPSHOT.jar app.jar

# Exponiere den Port, auf dem die Anwendung läuft
EXPOSE 8080

# Starte die Anwendung beim Ausführen des Containers
ENTRYPOINT ["java", "-jar", "app.jar"]
