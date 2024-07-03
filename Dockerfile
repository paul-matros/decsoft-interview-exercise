# 1st stage
FROM maven:3-eclipse-temurin-17 as builder

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn package

# 2nd stage
FROM openjdk:17

COPY --from=builder /usr/src/app/target/*.jar app.jar

COPY entrypoint.sh .
RUN ["chmod", "777", "entrypoint.sh"]

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=*:8787,server=y,suspend=n
ENTRYPOINT ["bash","entrypoint.sh"]

#uslugi
EXPOSE 8080
#debug
EXPOSE 8787