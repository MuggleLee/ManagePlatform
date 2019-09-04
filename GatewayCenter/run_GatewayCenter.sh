#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
docker ps -a | sed '/^CONTAINER/d' | grep "gateway" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "GatewayCenter" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 8093:8093 -e "SPRING_PROFILES_ACTIVE=dev" --name=GatewayCenter com.hao/gateway-center:1.0;
