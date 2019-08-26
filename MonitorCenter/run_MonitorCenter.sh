#!/bin/bash
#Date: 2019-08-26
#Author: MuggleLee
#Version: v1.0
docker ps -a | sed '/^CONTAINER/d' | grep "monitor" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "monitor" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 9001:9001 -e JAVA_OPTS='-Xmx32m' --name=MonitorCenter com.hao/monitor-center:1.0;
