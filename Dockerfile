FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} contrat-service.jar
ENTRYPOINT ["java","-jar","/contrat-service.jar"]
EXPOSE 9005