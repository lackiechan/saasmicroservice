Port=8961
JarName=base-eureka-1.0-SNAPSHOT.jar
LogsPatch=/logs

ID=`ps -ef | grep $Port | grep -v "grep" | awk '{print $2}'`
echo $ID
echo "---------------"
for id in $ID
do
kill -s 9 $id
echo "killed $id"
done
echo "---------------"

nohup java -jar -Dlogging.path=$LogsPatch  $JarName>$LogsPatch/base-eureka.out 2>&1 &

tail -f $LogsPatch/base-eureka.out

nohup java -jar /home/pro/application/monitor/zop-monitor-web.jar  >/logs/zop-monitor-web.log &

 >/logs/flyer-gateway.log &




    nohup java -jar base-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=prod >/usr/local/hm/logs/base-eureka.log &
    nohup java -jar base-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=prod --server.port=8961 >/usr/local/hm/logs/base-eureka.log &

    nohup java -jar flyer-gateway-1.0-SNAPSHOT.jar --spring.profiles.active=prod >/usr/local/hm/logs/flyer-gateway.log &
    nohup java -jar flyer-gateway-1.0-SNAPSHOT.jar --spring.profiles.active=prod --server.port=8016 >/usr/local/hm/logs/flyer-gateway.log &

    nohup java -jar  flyer-project-service-restapi-1.0-SNAPSHOT.jar --spring.profiles.active=prod >/usr/local/hm/logs/flyer-project-service-restapi.log &

    nohup java -jar  dev-generator-1.0-SNAPSHOT.jar --spring.profiles.active=prod  >/usr/local/hm/logs/dev-generator.log &

    nohup java -jar  flyer-user-service-domain-1.0.0.jar --spring.profiles.active=prod  >/usr/local/hm/logs/flyer-user-service-domain.log &


    nohup java -jar base-monitor-1.0-SNAPSHOT.jar --spring.profiles.active=prod  >/usr/local/hm/logs/base-minitor.log &

     nohup java -jar  flyer-api-mgr-service-1.0.0.jar --spring.profiles.active=prod >/usr/local/hm/logs/flyer-api-mgr-service.log &


     mvn clean package -P product


     ---test---
      nohup java -jar base-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test >/usr/local/hm/logs/base-eureka.log &
         nohup java -jar base-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=test --server.port=8962 >/usr/local/hm/logs/base-eureka.log &

         nohup java -jar flyer-gateway-1.0-SNAPSHOT.jar --spring.profiles.active=test >/usr/local/hm/logs/flyer-gateway.log &
         nohup java -jar flyer-gateway-1.0-SNAPSHOT.jar --spring.profiles.active=test --server.port=8016 >/usr/local/hm/logs/flyer-gateway.log &

         nohup java -jar  flyer-project-service-restapi-1.0-SNAPSHOT.jar --spring.profiles.active=test >/usr/local/hm/logs/flyer-project-service-restapi.log &

         nohup java -jar  dev-generator-1.0-SNAPSHOT.jar --spring.profiles.active=test  >/usr/local/hm/logs/dev-generator.log &

         nohup java -jar  flyer-user-service-domain-1.0.0.jar --spring.profiles.active=test  >/usr/local/hm/logs/flyer-user-service-domain.log &


         nohup java -jar base-monitor-1.0-SNAPSHOT.jar --spring.profiles.active=test  >/usr/local/hm/logs/base-minitor.log &


         nohup java -jar base-zipkin-1.0-SNAPSHOT.jar --spring.profiles.active=test  >/usr/local/hm/logs/base-zipkin.log &


         nohup java -jar  flyer-api-mgr-service-1.0.0.jar --spring.profiles.active=test >/usr/local/hm/logs/flyer-api-mgr-service.log &




