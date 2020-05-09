FROM openjdk:8-jre
MAINTAINER HuRonghua <1505523898@qq.com>

RUN mkdir /app

COPY mall-admin-1.0.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=dev"]

EXPOSE 8888
