#!/bin/bash

docker stack  deploy -c  docker-stack.yml charon-spring-boot-admin-server-cluster

docker service update charon-spring-boot-admin-server-cluster_charon-spring-boot-admin-server --force