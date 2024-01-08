FROM openjdk:17-jdk-alpine

RUN adduser --system boot && addgroup --system boot && adduser boot boot

USER boot

WORKDIR /app

COPY target/application.jar ./application.jar

EXPOSE 8080

ENTRYPOINT java -jar application.jar
