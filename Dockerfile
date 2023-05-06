FROM openjdk:8
FROM maven:3.5.2-jdk-8-alpine

WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

COPY . /app
#RUN mvn -v
#RUN mvn clean install -DskipTests
RUN mvn package
RUN ls
WORKDIR /app/target
RUN ls
#ARG JAR_FILE=Iwas_Web_Service.jar
#COPY ${JAR_FILE} app.jar
#COPY ./target/Iwas_Web_Service.jar app.jar
#ADD ./target/Iwas_Web_Service.jar /developments/

EXPOSE 9001
#ENTRYPOINT ["java","-jar","/target/Iwas_Web_Service.jar"]
ENTRYPOINT ["java","-jar","Gst_Web_Service.jar"]

#Run Command
#docker build --tag=iwas-accounts-spring:latest
#docker run -d -p 9080:9080 iwas-accounts-spring:latest