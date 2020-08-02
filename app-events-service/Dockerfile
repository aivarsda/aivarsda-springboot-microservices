FROM java:8-jre
MAINTAINER Aivars <aivars.dalderis@gmail.com>

ADD ./build/libs/app-events-service.jar /app/
CMD [ "sh", "-c", "java $JAVA_OPTS -jar /app/app-events-service.jar" ]

EXPOSE 8080