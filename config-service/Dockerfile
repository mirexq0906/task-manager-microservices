FROM openjdk:17-oracle
WORKDIR /opt/app
COPY build/libs/config-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]