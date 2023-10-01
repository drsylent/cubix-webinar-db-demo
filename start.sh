#!/bin/sh

if [ "$SPRING_PROFILES_ACTIVE" = "cloud" ]; then
	exec java -javaagent:/opt/app/opentelemetry.jar -Dspring.main.banner-mode=off -jar app.jar
else
	exec java -jar app.jar
fi
