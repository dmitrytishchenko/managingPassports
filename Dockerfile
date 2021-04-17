FROM openjdk:8-jdk-alpine
#за основу взята 8 версия джавы
ARG JAR_FILE=target/ManagingPassports-1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

