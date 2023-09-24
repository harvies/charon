#!/bin/bash
source set_jdk.sh
mvn clean  deploy -Paliyun -DskipTests=true -pl $1 -am