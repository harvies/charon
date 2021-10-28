#!/bin/bash
docker run -d --name elasticsearch6 -p 19200:9200 -p 19300:9300  -v /data/elasticsearch6/data:/usr/share/elasticsearch/data -v /data/elasticsearch6/plugins:/usr/share/elasticsearch/plugins -v /data/elasticsearch6/logs:/usr/share/elasticsearch/logs -e discovery.type=single-node  -e ES_JAVA_OPTS='-Xms512m -Xmx512m' elasticsearch:6.8.15
