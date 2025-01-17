FROM openjdk:17
MAINTAINER chaupez-dev
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-notas-0.0.1.jar
ENTRYPOINT ["java","-jar","/api-notas-0.0.1.jar"]