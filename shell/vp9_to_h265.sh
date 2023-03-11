#!/bin/bash

# usage
# ./vp9_to_h265.sh input.vp9 output.mp4

# 检查命令行参数
if [ $# -lt 1 ]; then
  echo "Usage: $0 input_file [output_file]"
  exit 1
fi

# 获取输入文件名和输出文件名
input_file="$1"
output_file="${2:-${input_file%.*}.mp4}"

# 转码命令
ffmpeg -i "$input_file" -c:v libx265 -crf 0 -preset ultrafast "$output_file"