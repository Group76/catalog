ARG CI_REGISTRY
ARG GOLDEN_IMG_TAG
ARG GOLDEN_IMG_JAVA_VM

ARG DD_JAVA_AGENT

FROM azul/zulu-openjdk:22 as builder

RUN mkdir -p /app/app/

COPY target/catalog-api.jar /app/app/application.jar

ENV NR_APP_NAME "catalog-api"
#
EXPOSE 8080 8081
#
CMD ["java -Ddd.integration.kotlin_coroutine.experimental.enabled=true -Dspring.config.activate.on-profile=${SPRING_PROFILES_ACTIVE} -Duser.timezone=${TIME_ZONE} -jar /app/app/application.jar ${KUBERNETES_SERVICE_HOST:-notdefined}"]