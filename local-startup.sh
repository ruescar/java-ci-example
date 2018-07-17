#!/usr/bin/env bash
set -e

echo ">>> Building App"
mvn clean install -DskipTests

echo ">>> Building Docker image"
docker build -t app-image ./app-server

echo ">>> Stopping Docker containers if running"
docker rm -f $(docker ps -q --filter 'name=app-container') || true > /dev/null

echo ">>> Starting Docker container"
docker run --rm -d --name app-container -p 8090:8090 app-image

