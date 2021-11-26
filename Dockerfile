FROM gradle:7-jdk17-alpine
COPY . /app
WORKDIR /app
RUN gradle build

CMD java -jar build/libs/exchange-0.0.1-SNAPSHOT.jar
