FROM openjdk:8-jdk-alpine
COPY target/multithreaded-jar-with-dependencies.jar multithreaded-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "multithreaded-jar-with-dependencies.jar"]
