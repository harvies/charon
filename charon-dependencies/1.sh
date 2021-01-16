curl -XPUT 'http://localhost:9200/_all/_settings?preserve_existing=true' -d '{ 
  "index.search.slowlog.threshold.fetch.warn" : "1s",
  "index.search.slowlog.threshold.query.warn" : "1s" 
}'
