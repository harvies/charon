#!/bin/bash

docker stack  deploy -c  docker-stack.yml spring-boot-admin

docker service update spring-boot-admin_spring-boot-admin --force