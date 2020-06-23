FROM java:8
VOLUME /tmp
WORKDIR /app
ADD base-eureka-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8961
ENTRYPOINT ["java", "-Xmx600m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#docker build -t eurekatest:latest
#docker run --name nginxtest -p 80:80 -d nginx
