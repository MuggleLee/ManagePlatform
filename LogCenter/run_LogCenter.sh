#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 8088:8088 --name=logcenter com.hao/logcenter:1.0;
