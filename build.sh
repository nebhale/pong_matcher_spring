#!/bin/bash

set -ex

docker pull docker.gocd.cf-app.com:5000/pong-matcher-spring || true
docker build -t docker.gocd.cf-app.com:5000/pong-matcher-spring .
docker push docker.gocd.cf-app.com:5000/pong-matcher-spring
