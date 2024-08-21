FROM container-registry.oracle.com/graalvm/jdk:17
WORKDIR /app
COPY /target/liability.jar /app/liability.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/liability.jar"]