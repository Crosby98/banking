FROM openjdk:8

ADD ./target/banking-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/banking-0.0.1-SNAPSHOT.jar"]

EXPOSE 9001