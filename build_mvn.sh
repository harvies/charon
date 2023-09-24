#!/bin/bash
source set_jdk.sh
mvn clean package -s .m2/settings.xml  -U -DskipTests
