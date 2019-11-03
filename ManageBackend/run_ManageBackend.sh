#!/bin/bash
#Date: 2019-09-03
#Author: MuggleLee
#Version: v1.0
docker ps -a | sed '/^CONTAINER/d' | grep "ManageBackend" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "manage-backend" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package -P dev docker:build -Dmaven.test.skip;
