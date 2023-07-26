FROM amazoncorretto:11

ENV BOT_NAME=VALUE
ENV BOT_TOKEN=VALUE

ARG JAR_FILE=target/*.jar
COPY $JAR_FILE telegrambot.jar
ENTRYPOINT ["java", "-Dbot.name=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/telegrambot.jar"]