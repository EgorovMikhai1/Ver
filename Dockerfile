FROM openjdk:17-jdk
COPY target/ver.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Ver.jar"]