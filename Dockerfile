# Etapa de Construção
FROM maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar o arquivo pom.xml e as dependências antes de copiar o código-fonte
COPY pom.xml .
COPY src ./src

# Executar o Maven diretamente para fazer o build
RUN mvn clean package -DskipTests

# Etapa de Execução
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar o JAR gerado da etapa de build para a etapa de execução
COPY --from=build /app/target/*.jar app.jar
EXPOSE 3333

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]