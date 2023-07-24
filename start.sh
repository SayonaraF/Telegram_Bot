#!/bin/bash

# Pull new changes
#git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_NAME_B=$1
export BOT_TOKEN_B=$2

# Start new deployment
docker-compose up --build -d