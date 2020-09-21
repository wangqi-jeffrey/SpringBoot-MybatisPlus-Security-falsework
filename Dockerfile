FROM registry.cn-beijing.aliyuncs.com/hfq-public/oraclejdk:11.0.8

RUN mkdir -p /data/www/test;\
    mkdir -p /data/logs/test

WORKDIR /data/www/test

COPY config/ config
COPY ./build/libs/*.jar test.jar

ENTRYPOINT ["/usr/local/jdk/bin/java", "-Dfile.encoding=UTF-8", "-jar", "test.jar"]