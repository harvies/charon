#!/bin/bash
source set_jdk.sh
mvn clean install -s .m2/settings.xml -DskipTests -U
