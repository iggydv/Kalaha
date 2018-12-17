FROM openjdk:8-jdk-alpine

ADD kalaha-web/target/kalaha-web-1.3.6.RELEASE.jar kalaha.jar
ENTRYPOINT [ "sh", "-c", "java -jar /kalaha.jar" ]