FROM openjdk:17-oracle
WORKDIR /opt/app
COPY build/libs/discovery-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]