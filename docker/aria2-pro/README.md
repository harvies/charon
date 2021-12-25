docker run -d \
--name aria2-pro \
--restart unless-stopped \
--log-opt max-size=1m \
-e PUID=$UID \
-e PGID=$GID \
-e UMASK_SET=022 \
-e RPC_SECRET=123456 \
-e RPC_PORT=6800 \
-p 6900:6800 \
-e LISTEN_PORT=6888 \
-p 6988:6888 \
-p 6988:6888/udp \
-v /volume3/aria2-config:/config \
-v /volume2/aria2-downloads:/downloads \
p3terx/aria2-pro



docker run -d \
--name ariang \
--log-opt max-size=1m \
--restart unless-stopped \
-p 6980:6880 \
p3terx/ariang