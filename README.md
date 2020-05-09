# mall
线上地址：http://119.23.105.18:1111


前端项目： https://github.com/17623252837/mall-web
## RBAC 权限控制模型
|工程名/项目名|类型|服务端|客户端|端口|描述|
|-------|-------|-------|-------|-------|-------|
|mall|pom|否|否|无|案例工程|
|mall-admin|module|否|否|8888|程序入口|
|mall-common|module|否|否|无|工具类模块|
|mall-databases|未加入模块控制|否|否|无|代码生成模块|
|mall-domain|module|否|否|无|实体类领域模型|
|mall-mapper|module|否|否|无|sql 处理模块|
|mall-security|module|否|否|无|权限处理模块|
|mall-service|module|否|否|无|业务逻辑处理模块|

## 技术栈
|框架|作用|版本|
|-------|-------|-------|
|spring-boot|不解释|2.1.7.RELEASE|
|spring-security-oauth2|权限控制|2.1.2.RELEASE|
|tk-mybatis|轻量级ORM框架|2.1.5|
|oss|阿里云存储|3.6.0|
|okhttp|轻量级网络请求框架|1.18.10|
|swagger2|文档引擎|2.7.0|
|springfox-swagger-ui|页面ui|2.7.0|
|easyexcel|轻量级excel处理框架|2.2.3|


## 服务器（本地）
|服务器名|地址|作用|
|-------|-------|-------|
|Ubuntu docker Gitlab|192.168.88.136:80|代码托管平台|
|Ubuntu docker Nexus|192.168.88.135:8081|maven 私服|
|Ubuntu docker Registry|192.168.88.130:8080|docker 私服|
|Ubuntu docker runner|192.168.88.137|持续集成,与gitlab配合完成持续集成|

##  .gitlab-ci.yml
```yml
stages:
  - build
  - push
  - run
  - clean

build:
  stage: build
  script:
    - /usr/local/maven/apache-maven-3.6.1/bin/mvn clean package
    - cp ./mall-admin/target/mall-admin-1.0.0-SNAPSHOT.jar docker
    - cd docker
    - docker build -t 192.168.88.130:5000/mall-admin .

push:
  stage: push
  script:
    - docker push 192.168.88.130:5000/mall-admin

run:
  stage: run
  script:
    - cd docker
    - docker-compose down
    - docker-compose up -d

clean:
  stage: clean
  script:
    - docker rmi $(docker images -q -f dangling=true)

```

## docker-compose.yml
```yml
version: '3.1'
services:
  mall-monomer:
    restart: always
    image: 192.168.88.130:5000/mall-admin
    container_name: mall-admin-1.0.0-SNAPSHOT
    ports:
      - 9999:8888
    networks:
      - cloud

networks:
  cloud:
    external:
      name: spring_cloud

```

## Dockerfile

```$xslt
FROM openjdk:8-jre
MAINTAINER HuRonghua <1505523898@qq.com>

RUN mkdir /app

COPY mall-admin-1.0.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=dev"]

EXPOSE 8888

```

### 阉割版半自动持续集成
```$xslt
FROM openjdk:8-jdk-alpine
ADD ./mall-admin/target/mall-admin-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

```
