#!/bin/bash
#Date: 2019-08-21
#Author: MuggleLee
#Version: v1.0
mvn clean package docker:build -Dmaven.test.skip;
docker run -d -p 8081:8081 --name=config com.hao/configcenter:1.0;
