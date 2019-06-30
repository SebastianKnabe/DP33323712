
FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /shop
WORKDIR /shop
Run gradle build