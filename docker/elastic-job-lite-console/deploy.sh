#!/bin/bash

git pull
docker stack deploy   --compose-file  docker-stack.yml elastic-job-lite-console