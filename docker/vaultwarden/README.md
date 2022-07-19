docker pull vaultwarden/server:latest
docker run -d --name vaultwarden -v /volume2/docker/vaultwarden:/data/ -p 84:80  --restart=always vaultwarden/server:latest