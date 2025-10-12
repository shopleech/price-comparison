FROM alpine:3.18 as production-stage

ARG APP_ENV
ARG APP_NAME="public-api"
ARG APP_VERSION="1.0"
ARG JAR_FILE="${APP_NAME}-${APP_VERSION}.jar"
ARG WORK_DIR="/home/sl/public-api"

RUN apk add --no-cache openjdk17-jre python3 py3-pip aws-cli logrotate jq procps iputils bash
RUN adduser -D sl
RUN mkdir -p "${WORK_DIR}"

RUN { \
        echo '#!/bin/bash'; \
        echo "echo 'it happened'"; \
    } > /usr/local/bin/app.sh
RUN chmod +x /usr/local/bin/app.sh

WORKDIR "${WORK_DIR}"
COPY "/build/libs/${JAR_FILE}" "public-api.jar"
RUN chown -R sl:sl "${WORK_DIR}"

RUN echo 1
EXPOSE 8080
ENV RUN_IN_CONTAINER="True"

USER sl

ENTRYPOINT ["/bin/bash", "-c", "/usr/local/bin/app.sh && java -jar public-api.jar"]
