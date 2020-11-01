FROM nextcloud:20.0.1
LABEL maintainer="delverjw@gmail.com"

RUN apt-get update -y && apt-get install -y unrar-free p7zip p7zip-full ffmpeg smbclient

## libreoffice
RUN apk --no-cache add \
    --repository http://dl-cdn.alpinelinux.org/alpine/edge/testing \
    libreoffice \
    ttf-dejavu

RUN apt-get -y clean
