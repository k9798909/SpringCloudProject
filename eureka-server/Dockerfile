FROM openjdk:17
WORKDIR /app
COPY target/eureka-server.jar .
ENV PORT=8087
EXPOSE 8087
CMD ["java", "-jar","-Dspring.profiles.active=prod", "eureka-server.jar"]