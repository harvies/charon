#!/bin/bash

docker stack  deploy -c  docker-stack.yml charon-eureka-server-cluster

docker service update charon-eureka-server_eureka1 --force

sleep 10s

docker service update charon-eureka-server_eureka2 --force

sleep 10s

docker service update charon-eureka-server_eureka3 --force