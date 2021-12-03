FROM openjdk
COPY ./target/nuitDinfo-0.0.1-SNAPSHOT.jar .
ENTRYPOINT exec java -jar nuitDinfo-0.0.1-SNAPSHOT.jar
EXPOSE 9000