FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
# Ajuste o nome do JAR conforme definido no seu build.gradle.kts
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]