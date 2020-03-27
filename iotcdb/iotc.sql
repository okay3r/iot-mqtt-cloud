DROP TABLE mqtt_pub;;/*SkipError*/
CREATE TABLE mqtt_pub(
    id BIGINT(19) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    username VARCHAR(32) NOT NULL   COMMENT '用户名' ,
    topic VARCHAR(32) NOT NULL   COMMENT '主题' ,
    payload VARCHAR(32) NOT NULL   COMMENT '消息' ,
    qos INT NOT NULL   COMMENT 'qos' ,
    success INT NOT NULL   COMMENT '是否成功 1是，0否' ,
    time DATETIME NOT NULL   COMMENT '时间' ,
    PRIMARY KEY (id)
) COMMENT = '发布消息记录 ';;

ALTER TABLE mqtt_pub COMMENT '发布消息记录';;
DROP TABLE client_topic_info;;/*SkipError*/
CREATE TABLE client_topic_info(
    id BIGINT(19) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    client_id VARCHAR(32) NOT NULL   COMMENT '客户端id' ,
    node VARCHAR(32) NOT NULL   COMMENT 'mqtt节点' ,
    topic VARCHAR(32) NOT NULL   COMMENT '主题' ,
    qos INT NOT NULL   COMMENT 'qos' ,
    client_name INT    COMMENT '客户端名称' ,
    remark VARCHAR(1024) NOT NULL   COMMENT '备注' ,
    update_time DATETIME NOT NULL   COMMENT '修改时间' ,
    PRIMARY KEY (id)
) COMMENT = '客户端对应主题 ';;

ALTER TABLE client_topic_info COMMENT '客户端对应主题';;
DROP TABLE mqtt_sub;;/*SkipError*/
CREATE TABLE mqtt_sub(
    id BIGINT(19) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    topic VARCHAR(32) NOT NULL   COMMENT '主题' ,
    qos INT NOT NULL   COMMENT 'qos' ,
    payload VARCHAR(32) NOT NULL   COMMENT '消息' ,
    time DATETIME NOT NULL   COMMENT '时间' ,
    PRIMARY KEY (id)
) COMMENT = '订阅消息记录 ';;

ALTER TABLE mqtt_sub COMMENT '订阅消息记录';;
DROP TABLE user;;/*SkipError*/
CREATE TABLE user(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT '用户id' ,
    username VARCHAR(32) NOT NULL   COMMENT '用户名' ,
    password VARCHAR(32) NOT NULL   COMMENT '密码' ,
    email VARCHAR(32) NOT NULL   COMMENT '邮箱' ,
    phone VARCHAR(32) NOT NULL   COMMENT '电话' ,
    is_admin INT NOT NULL   COMMENT '是否为管理员 1是，0否' ,
    remark VARCHAR(1024) NOT NULL   COMMENT '备注' ,
    create_time DATETIME NOT NULL   COMMENT '创建时间' ,
    update_time DATETIME NOT NULL   COMMENT '修改时间' ,
    PRIMARY KEY (id)
) COMMENT = '用户表 ';;

ALTER TABLE user COMMENT '用户表';;
DROP TABLE user_operation;;/*SkipError*/
CREATE TABLE user_operation(
    id BIGINT(19) NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    op VARCHAR(32) NOT NULL   COMMENT '操作指令' ,
    address VARCHAR(32) NOT NULL   COMMENT '目标ip' ,
    time DATETIME NOT NULL   COMMENT '操作时间' ,
    PRIMARY KEY (id)
) COMMENT = '用户操作记录 ';;

ALTER TABLE user_operation COMMENT '用户操作记录';;
