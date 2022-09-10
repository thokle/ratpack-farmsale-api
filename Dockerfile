FROM java:8-jdk-alpine

COPY build/distributions/ratpack-farmsale-api.zip /usr/lib/farmsale/ratpack-farmsale-api.zip

RUN cd /usr/lib/farmsale/ && unzip ratpack-farmsale-api.zip

RUN rm /usr/lib/farmsale/ratpack-farmsale-api.zip

RUN /usr/lib/farmsale/ratpack-farmsale-api/bin/ratpack-farmsale-api


