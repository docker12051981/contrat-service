FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} contrat-service.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo1:27017,mongo2:27027,mongo3:27037/?replicaSet=rs0&retryWrites=true/contrat_db","-jar","/contrat-service.jar"]
EXPOSE 9005