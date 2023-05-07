FROM eclipse-temurin:17 AS builder

RUN mkdir /opt/build
WORKDIR /opt/build
COPY .mvn .mvn
COPY src src
COPY ./* ./
RUN chmod +x ./mvnw && ./mvnw clean verify

FROM eclipse-temurin:17-jre
RUN mkdir /opt/app && chown 1001 -R /opt/app
USER 1001
WORKDIR /opt/app
COPY --chown=1001 --from=builder /opt/build/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
