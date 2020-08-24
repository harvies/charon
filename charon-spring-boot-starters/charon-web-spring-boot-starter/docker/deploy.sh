#!/bin/bash

docker stack  deploy -c  docker-stack.yml charon-boot-example

docker service update --with-registry-auth charon-boot-example_charon-boot-example --force