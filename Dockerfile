FROM openjdk:17-jdk-slim
WORKDIR /clinica_medica
COPY target/com.clinica-medica-0.0.1-SNAPSHOT.jar clinica_medica.jar
EXPOSE 8080
CMD ["java", "-jar", "clinica_medica.jar"]
