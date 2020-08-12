DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id    BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT(11)     NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

INSERT INTO user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@xxx.com'),
       (2, 'Jack', 20, 'test2@xxx.com'),
       (3, 'Tom', 28, 'test3@xxx.com'),
       (4, 'Sandy', 21, 'test4@xxx.com'),
       (5, 'Billie', 24, 'test5@xxx.com');