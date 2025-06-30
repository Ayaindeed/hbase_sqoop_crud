# Use Maven to build
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Use a slim JRE to run
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*-jar-with-dependencies.jar app.jar
EXPOSE 5005
ENTRYPOINT ["java", "-jar", "app.jar"]
