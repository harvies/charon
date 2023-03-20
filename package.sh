#!/bin/bash
source ./set_jdk17.sh
mvn clean package -s .m2/settings.xml  -U  -DskipTests