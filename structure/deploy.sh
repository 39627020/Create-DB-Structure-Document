#!/bin/bash
mvn package -Dmaven.test.skip=true
docker-compose up --build -d