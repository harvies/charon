#!/bin/bash
source ./set_jdk17.sh
mvn clean  deploy -Paliyun -DskipTests=true -pl $1 -am