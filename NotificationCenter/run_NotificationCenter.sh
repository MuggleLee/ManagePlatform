#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
docker ps -a | sed '/^CONTAINER/d' | grep "NotificationCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "notification-center" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
#docker run -d -p 8090:8090 --restart=always -e JAVA_OPTS='-Xmx128m' -m 400m --memory-swap -1 --name=NotificationCenter com.hao/notification-center:1.0;
