#!/bin/bash

echo 'stop alioth service'
docker ps -f name=alioth | awk '{print $1}' | grep -v CONTAINER | xargs docker stop

echo 'cd /root/alioth and remove target/'
cd /root/alioth
rm -rf target

echo 'git pull'
git pull

echo 'mvn clean package'
mvn clean package

echo 'mv target/ROOT.war to /root/tomcat/webapps/'
rm -rf /root/tomcat/webapps
mkdir -p /root/tomcat/webapps

mv target/ROOT.war /root/tomcat/webapps/

echo 'docker run tomcat'
docker run -d -p 80:8080 --name alioth -v /root:/root -v /root/tomcat/webapps/:/usr/local/tomcat/webapps -v /root/tomcat/logs:/usr/local/tomcat/logs tomcat
