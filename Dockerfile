FROM azul/zulu-openjdk:17 as builder

RUN mkdir -p /app/app/

COPY build/libs/*.jar /app/app/application.jar

## Examples of variable usage
ENV NR_APP_NAME "catalog-api"
#
EXPOSE 8080 8081
#
CMD ["java $DD_JAVA_AGENT -Ddd.integration.kotlin_coroutine.experimental.enabled=true -Dspring.config.activate.on-profile=${SPRING_PROFILES_ACTIVE} -Duser.timezone=${TIME_ZONE} -jar /app/app/application.jar ${KUBERNETES_SERVICE_HOST:-notdefined}"]