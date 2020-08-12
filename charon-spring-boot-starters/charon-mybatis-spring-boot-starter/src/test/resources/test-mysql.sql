use test;
-- auto-generated definition
create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(64) null,
    password varchar(64) null
);


INSERT INTO test.user (id, username, password) VALUES (1, 'admin', '123456');
INSERT INTO test.user (id, username, password) VALUES (4, 'user5', 'password5');
INSERT INTO test.user (id, username, password) VALUES (5, 'user0', 'password0');
INSERT INTO test.user (id, username, password) VALUES (6, 'user2', 'password2');
INSERT INTO test.user (id, username, password) VALUES (7, 'user4', 'password4');
INSERT INTO test.user (id, username, password) VALUES (8, 'user6', 'password6');
INSERT INTO test.user (id, username, password) VALUES (9, 'user8', 'password8');
INSERT INTO test.user (id, username, password) VALUES (10, 'user5', 'password5');
INSERT INTO test.user (id, username, password) VALUES (11, 'user5', 'password5');
INSERT INTO test.user (id, username, password) VALUES (12, 'user5', 'password5');
INSERT INTO test.user (id, username, password) VALUES (13, 'user5', 'password5');