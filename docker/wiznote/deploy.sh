docker run --name wiz --restart=always -it -d -v  /volume3/data/wizdata:/wiz/storage -v  /etc/localtime:/etc/localtime -p 83:80 -p 9269:9269/udp  viticis/wizserver:latest
