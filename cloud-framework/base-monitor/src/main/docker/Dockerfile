FROM java:8
VOLUME /tmp
ADD base-monitor-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8963
ENTRYPOINT ["java", "-Xmx600m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]