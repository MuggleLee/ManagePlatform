#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 8086:8761 --name=eureka com.hao/registercenter:1.0;
