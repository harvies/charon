#!/bin/bash
source set_jdk.sh
mvnd clean install -s .m2/settings.xml -DskipTests -U
