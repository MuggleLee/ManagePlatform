#!/bin/bash
#Date: 2019-10-21
#Author: MuggleLee
#Version: v1.0
#Description:将所有子项目都打包后构建成镜像

#父工程 pom 文件所在目录，打包编译，将依赖包放至本地仓库
mvn clean install package -Dmaven.test.skip

cd RegisterCenter
docker ps -a | sed '/^CONTAINER/d' | grep "register" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "register" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../ConfigCenter

docker ps -a | sed '/^CONTAINER/d' | grep "config" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "config" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../ManageBackend

docker ps -a | sed '/^CONTAINER/d' | grep "ManageBackend" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "manage-backend" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package -P dev docker:build -Dmaven.test.skip;
cd ../LogCenter

docker ps -a | sed '/^CONTAINER/d' | grep "LogCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "log-center" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../NotificationCenter

docker ps -a | sed '/^CONTAINER/d' | grep "NotificationCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "notification-center" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../UserCenter

docker ps -a | sed '/^CONTAINER/d' | grep "UserCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "user-center" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../OauthCenter

docker ps -a | sed '/^CONTAINER/d' | grep "OauthCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "oauth" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../GatewayCenter

docker ps -a | sed '/^CONTAINER/d' | grep "GatewayCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "GatewayCenter" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../FileCenter

docker ps -a | sed '/^CONTAINER/d' | grep "FileCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "file-center" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
cd ../MonitorCenter

docker ps -a | sed '/^CONTAINER/d' | grep "MonitorCenter" | gawk '{cmd="docker rm -f "$1; system(cmd)}'
docker images | sed '/^IMAGE/d' | grep "monitor" | gawk '{cmd="docker rmi "$3; system(cmd)}'
mvn clean package docker:build -Dmaven.test.skip;
