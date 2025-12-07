FROM gradle:8.5.0-jdk17 AS build
WORKDIR /app

# Copia tudo do projeto
COPY . .

# Permissão para o gradlew
RUN chmod +x gradlew

# Build sem testes
RUN ./gradlew clean build -x test --no-daemon

# ---------------------
# Runtime image
# ---------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR exato gerado pelo build
COPY --from=build /app/build/libs/glycotrack-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


