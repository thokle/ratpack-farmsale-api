FROM openjdk:8-jre-alpine
MAINTAINER Thomas Kleist <thomas@kleist-it.com>
COPY build/distributions/ratpack-farmsale-api.zip /opt/ratpack-farmsale-api/
RUN unzip /opt/ratpack-farmsale-api/ratpack-farmsale-api.zip -d /opt/ratpack-farmsale-api
WORKDIR /opt/ratpack-farmsale-api/ratpack-farmsale-api
EXPOSE 8889
CMD ["./bin/ratpack-farmsale-api", "-fg"]


