#!/bin/bash
#Date: 2019-09-04
#Author: MuggleLee
#Version: v1.0
docker ps -a | sed '/^CONTAINER/d' | grep "FileCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "file-center" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 8089:8089 -e "SPRING_PROFILES_ACTIVE=dev": --name=FileCenter com.hao/file-center:1.0;
