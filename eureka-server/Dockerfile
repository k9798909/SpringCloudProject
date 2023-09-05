FROM maven:3.6.3-openjdk-17-slim AS build-stage
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Pprod -Dmaven.test.skip

FROM openjdk:17-alpine AS production-stage
WORKDIR /app
COPY --from=build-stage /home/app/target/eureka-server.jar .
ENV PORT=8087
EXPOSE 8087
CMD ["java", "-jar","-Dspring.profiles.active=prod", "eureka-server.jar"]