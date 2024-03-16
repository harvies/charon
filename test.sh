#!/bin/bash
source set_jdk.sh
mvnd clean package -s .m2/settings.xml  -U

