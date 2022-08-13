# 每天凌晨3点执行
0 3 * * * sh /root/git/charon/shell/mysql_backup.sh > /dev/null 2>&1 &