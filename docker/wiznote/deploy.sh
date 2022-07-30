3步部署

1、

docker run --name wiz --restart=always -it -d -v  /home/wizdata:/wiz/storage -v  /etc/localtime:/etc/localtime -p 83:80 -p 9269:9269/udp  wiznote/wizserver

2、
docker exec -it wiz bash
cd /wiz/app/wizserver/node_modules/node-rsa/src/
rm -rf NodeRSA.js
wget https://raw.githubusercontent.com/yao177/sillygirl-plugin/main/NodeRSA.js
exit
docker restart wiz

3、
你的IP地址+8848访问。

结束

http://124.222.129.205/?p=446