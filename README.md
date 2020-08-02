## 编译

mvn clean install -DskipTests -U -s '.maven/settings.xml'

## 部署

mvn clean deploy -DskipTests -U -s '.maven/settings.xml'

![Maven Package](https://github.com/harvies/charon/workflows/Maven%20Package/badge.svg)