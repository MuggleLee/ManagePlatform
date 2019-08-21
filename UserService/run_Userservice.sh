#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
mvn clean package -P dev docker:build -Dmaven.test.skip;
docker run -d -p 8091:8091 -e "SPRING_PROFILES_ACTIVE=dev" --name=userservice com.hao/userservice:1.0;
