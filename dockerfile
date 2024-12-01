FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM gcr.io/distroless/java17-debian11

COPY --from=build /app/target/kapp-0.0.1-SNAPSHOT.jar /app.jar

CMD ["app.jar"]