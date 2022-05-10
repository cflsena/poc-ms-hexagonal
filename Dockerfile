FROM openjdk:8

ARG PROFILES
ARG JAVA_ADDITIONAL_OPTS

ENV PROFILES ${PROFILES}
ENV JAVA_ADDITIONAL_OPTS ${JAVA_ADDITIONAL_OPTS}

WORKDIR /opt/challenge-exacta-works/app

ENV DOCKERIZE_VERSION v0.6.1

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN echo "#!/bin/bash\n" \
         "java ${JAVA_ADDITIONAL_OPTS} -Dspring.profiles.active=${PROFILES} -jar app.jar" > entrypoint.sh

RUN chmod +x entrypoint.sh

COPY /back-end/adapters/target/*.jar app.jar

ENTRYPOINT ./entrypoint.sh