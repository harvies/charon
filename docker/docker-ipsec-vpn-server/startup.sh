#!/usr/bin/env bash
docker run \
    --name ipsec-vpn-server \
    --network dev_overlay \
    --env-file ./vpn.env \
    --restart=always \
    -p 500:500/udp \
    -p 4500:4500/udp \
    -d --privileged \
    hwdsl2/ipsec-vpn-server

## --network dev_overlay 是笔者swarm overlay网络，可不加