FROM maven:3.8.4-openjdk-17 AS build
COPY . .
Run mvn clean package -DskipTests

From openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]