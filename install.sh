#!/bin/bash
source ./set_jdk17.sh
mvn clean install -s .m2/settings.xml -DskipTests -U
