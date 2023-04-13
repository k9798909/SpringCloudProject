FROM openjdk:17
WORKDIR /app
COPY target/gatewar-server.jar .
ENV PORT=8087
EXPOSE 8087
CMD ["java", "-jar","-Dspring.profiles.active=prod", "gatewar-server.jar"]