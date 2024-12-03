FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app
COPY /src ./src
COPY pom.xml ./pom.xml
COPY settings.xml /root/.m2/settings.xml
RUN mvn clean package -DskipTests

FROM gcr.io/distroless/java17-debian11

COPY --from=build /app/target/*.jar /app.jar

CMD ["app.jar"]