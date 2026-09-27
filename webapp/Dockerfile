FROM tomcat:9.0-jdk11-corretto
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY ./registration-app.war /usr/local/tomcat/webapps/registration-app.war
EXPOSE 8080
