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
ENV OTEL_SERVICE_NAME=""
ADD --chown=1001 https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.20.2/opentelemetry-javaagent.jar opentelemetry.jar
COPY --chown=1001 --from=builder /opt/build/target/*.jar app.jar
COPY --chown=1001 start.sh start.sh
RUN chmod +x start.sh
ENTRYPOINT [ "./start.sh" ]
