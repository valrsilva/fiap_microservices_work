#!/bin/sh
while ! nc -z 192.168.15.25 8888 ; do
    echo "Waiting for the Config Server"
    sleep 3
done
while ! nc -z 192.168.15.25 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done
java -jar /opt/lib/kanban-issues-0.0.1-SNAPSHOT.jar