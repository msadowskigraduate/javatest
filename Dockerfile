FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/javatest-0.0.1-SNAPSHOT.jar javatest.jar
RUN sh -c 'touch /javatest.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /core.jar"]