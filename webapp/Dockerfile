FROM maven:3.8.5-openjdk-11-slim AS code-compiler
WORKDIR /build-context
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM tomcat:9.0-jdk11-corretto
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=code-compiler /build-context/target/registration-app.war /usr/local/tomcat/webapps/registration-app.war
EXPOSE 8080
