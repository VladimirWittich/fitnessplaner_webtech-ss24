#
# Build stage
#
FROM gradle:7.5.1-jdk17 AS build
WORKDIR /app
COPY . /app/
RUN JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java)))) && \
    export JAVA_HOME && \
    gradle build --no-daemon

#
# Package stage
#
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
