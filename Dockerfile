FROM openjdk:17
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ./target/SGS-0.0.1-SNAPSHOT.jar G-Stock.jar
ENTRYPOINT ["java","-jar","G-Stock.jar"]