FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD /target/*SNAPSHOT.jar rpg309.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/rpg309.jar"]
