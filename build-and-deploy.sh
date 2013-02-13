#!/bin/bash
@echo off

# This script will live in ~/build_and_deploy.sh with chmod +x
targetdir=~/GrowersNationSite

# Target/working directory
cd $targetdir

# Update the contents of the git repo from the origin
echo "Pulling the latest code from origin master"
git pull

# Stop the existing service (this may take some time so we do it before the Maven build)
echo Performing a soft kill of the existing process
pkill -1 site-develop-SNAPSHOT

# Build with Maven
echo Maven build the new code
mvn clean package

# Start the new background process
echo Starting the new process under nohup
nohup java -jar target/site-develop-SNAPSHOT.jar server gn.yml > /var/log/site/site-log.log 2>&1 &

echo Complete. Verify operation by visiting http://ec2-46-137-56-2.eu-west-1.compute.amazonaws.com/