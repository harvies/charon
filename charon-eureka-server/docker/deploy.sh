#!/bin/bash

docker stack  deploy -c  docker-stack.yml charon-eureka-server-cluster

docker service update charon-eureka-server-cluster_eureka1 --force

sleep 10s

docker service update charon-eureka-server-cluster_eureka2 --force

sleep 10s

docker service update charon-eureka-server-cluster_eureka3 --force