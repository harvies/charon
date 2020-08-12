# Mysql使用案例

## 外网访问

1，登进MySQL之后，

2，输入以下语句，进入mysql库：

use mysql
3，更新域属性，'%'表示允许外部访问：

update user set host='%' where user ='root';
4，执行以上语句之后再执行：

FLUSH PRIVILEGES;
5，再执行授权语句：

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'WITH GRANT OPTION;
然后外部就可以通过账户密码访问了。

## 清理表数据
```mysql
-- 清理表数据的方法

-- 创建和table1同表结构的表table1_new
CREATE TABLE table1_new LIKE table1;

-- 插入table1表的数据到table1_new表
INSERT  table1_new SELECT * FROM table1 WHERE gmt_create >= '2019-01-01 00:00:00';

-- 重命名table1表为table1_bak
RENAME TABLE  table1 TO table1_bak;
-- 重命名table1_new表为table1
RENAME TABLE  table1_new TO table1;

```
## mysql show columns 等show的用法
```sql
SHOW DATABASES; -- 列出 MySQL Server 上的资料库。
SHOW TABLES [FROM db_name]; -- 列出资料库的资料表。
SHOW TABLE STATUS [FROM db_name]; -- 列出资料库的资料表，提供比較详细的信息。
SHOW COLUMNS FROM tbl_name [FROM db_name]; -- 列出资料表，同 SHOW FIELDS FROM tbl_name [FROM db_name]，DESCRIBE tbl_name [col_name]。
SHOW FULL COLUMNS FROM tbl_name [FROM db_name]; -- 列出資料表的欄位，提供比較詳細的訊息，同 SHOW FULL FIELDS FROM tbl_name [FROM db_name]。
SHOW INDEX FROM tbl_name [FROM db_name]; -- 列出資料表的索引訊息。
SHOW STATUS; -- 列出 Server 的狀態訊息。
SHOW VARIABLES; -- 列出 MySQL 系統變數的值。
SHOW PROCESSLIST; -- 顯示哪個執行緒正在運行。
SHOW GRANTS FOR user; -- 列出對一個用戶必須發出以重複授權的授權命令
```

## 参考
https://www.jianshu.com/p/6919843ca796

https://blog.csdn.net/h996666/article/details/80921913