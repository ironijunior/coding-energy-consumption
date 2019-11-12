FROM maven:3.6.2-jdk-11
LABEL maintainer="ironimedina@gmail.com"
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package --batch-mode
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./application/target/energy-consumption.jar"]
