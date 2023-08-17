#!/bin/bash
source ./set_jdk17.sh
mvnd clean package -s .m2/settings.xml  -U
