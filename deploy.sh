#!/bin/bash
source set_jdk.sh
mvnd clean  deploy -Paliyun -DskipTests=true
