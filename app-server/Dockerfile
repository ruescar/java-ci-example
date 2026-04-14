FROM openjdk:8u212-jre-alpine3.9

EXPOSE 8090

COPY target/app-server-0.0.1-SNAPSHOT-exec.jar /opt/app.jar

CMD ["java", "-jar", "/opt/app.jar"]

