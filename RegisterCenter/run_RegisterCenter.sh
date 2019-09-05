#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
docker ps -a | sed '/^CONTAINER/d' | grep "register" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "register" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 8086:8086 -e JAVA_OPTS='-Xmx32m' --name=RegisterCenter com.hao/register-center:1.0;
