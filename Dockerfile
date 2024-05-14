FROM ubuntu:latest
LABEL authors="vladimirwittich"

# Aktualisiere die Paketquellen und installiere OpenJDK 16
RUN apt-get update && apt-get install -y openjdk-16-jdk

# Kopiere den Projektcode in den Container
COPY . .

# Führe den Gradle-Build aus
RUN ./gradlew bootJar --no-daemon

# Verwende OpenJDK 17 für die Ausführung der Anwendung
FROM openjdk:17-jdk-slim

# Exponiere den Port 8080 (wenn benötigt)
EXPOSE 8080

# Kopiere das erstellte JAR-Datei in den Container
COPY --from=build /build/libs/fitnessplaner-0.0.1-SNAPSHOT.jar app.jar

# Definiere den Befehl zum Ausführen der Anwendung
ENTRYPOINT ["java", "-jar", "app.jar"]
