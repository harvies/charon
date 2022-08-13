# 每天凌晨3点执行
0 3 * * * sh /home/mysql_backup.sh > /dev/null 2>&1 &