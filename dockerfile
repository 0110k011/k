#FROM maven:3.8.5-openjdk-17 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests

#FROM gcr.io/distroless/java17-debian11

#COPY --from=build /app/target/kapp-0.0.1-SNAPSHOT.jar /app.jar

#CMD ["java", "-jar", "/app.jar"]

# Etapa 1: Usar uma imagem base com o JDK para compilar o aplicativo
FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app

# Copiar o código-fonte para o container
COPY pom.xml .
COPY src ./src
COPY libs ./libs

# Construir o arquivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Usar uma imagem distroless com Java para rodar a aplicação
FROM gcr.io/distroless/java17-debian11

# Definir o local onde o JAR será copiado
COPY --from=build /app/target/*.jar /app/app.jar

# Expor a porta em que o Spring Boot estará ouvindo
EXPOSE 8080

# Comando para rodar o JAR
CMD ["java", "-jar", "/app/app.jar"]