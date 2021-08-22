FROM docker.seafile.top/seafileltd/seafile-pro-mc:8.0.8

LABEL maintainer="delverjw@gmail.com"

RUN apt-get update -y && apt-get install ffmpeg -y

RUN pip3 install pillow moviepy

WORKDIR /opt/seafile