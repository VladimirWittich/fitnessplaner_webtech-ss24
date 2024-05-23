FROM gradle:8-jdk17 as builder
WORKDIR /
COPY . ./
RUN gradle build

FROM openjdk:17-slim
LABEL authors="marwin.moellers"
COPY --from=builder build/libs .
ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.jar"]