#!/usr/bin/env bash

#Stop and remove all containers
./server/src/main/resources/scripts/stop_n_remove_containers.sh ;

#Set exit on failure flag
set -e

#Build project
cd ./server ;
./gradlew clean build docker ;
cd .. ;

#Run containers
docker-compose up