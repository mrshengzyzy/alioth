FROM maven:3-jdk-8-slim AS build
RUN sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list; \
    apt-get update; \
    apt-get install -y --no-install-recommends \
    apt-utils \
    git \
    ; \
    rm -rf /var/lib/apt/lists/*
RUN git clone https://github.com/mrshengzyzy/alioth.git
WORKDIR /alioth
COPY application.properties ./src/main/resources
RUN mvn clean package

FROM openjdk:8-jre

RUN mkdir -p /root/logs; \
    touch /root/fish.db
WORKDIR /alioth
COPY --from=build /alioth/target/alioth.jar ./
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xmx1024m","-Xms1024m","alioth.jar"]
