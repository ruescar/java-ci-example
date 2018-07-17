FROM openjdk:8-jre-alpine

EXPOSE 8090

COPY target/app-server-0.0.1-SNAPSHOT-exec.jar /opt/app.jar

CMD ["java", "-jar", "/opt/app.jar"]

