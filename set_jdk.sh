#!/bin/zsh

# 判断操作系统类型
OS_TYPE=$(uname -s)
if [ $OS_TYPE = "Darwin" ]; then
  # Mac系统
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk use java 17.0.12.fx-zulu
elif [ $OS_TYPE = "Linux" ]; then
  # Linux系统
  export JAVA_HOME=/usr/lib/jvm/java-22-openjdk
else
  # Windows系统
  export JAVA_HOME="C:\Program Files\Java\jdk-17"
fi

# 将JDK的bin目录添加到系统的PATH环境变量中
export PATH=$JAVA_HOME/bin:$PATH

# 打印JDK版本
java -version > /dev/null 2>&1
if [ $? -eq 0 ]; then
  echo "Java环境变量设置成功，当前Java版本为："
  java -version
else
  echo "Java环境变量设置失败，请检查Java安装路径是否正确"
fi