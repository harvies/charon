#!/bin/bash

# usage
# ./vp9_to_h265.sh input.vp9 output.mp4

# 定义输入文件名和输出文件名
input_file="input.vp9"
output_file="output.mp4"

# 如果命令行参数中指定了输入文件名，则使用命令行参数的值
if [ "$#" -gt 0 ]; then
  input_file="$1"
fi

# 如果命令行参数中指定了输出文件名，则使用命令行参数的值
if [ "$#" -gt 1 ]; then
  output_file="$2"
fi

# 转码命令
ffmpeg -i "$input_file" -c:v libx265 -crf 0 -preset ultrafast "$output_file"