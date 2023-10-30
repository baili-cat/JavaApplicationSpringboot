CREATE DATABASE `baili_test` DEFAULT CHARACTER SET utf8mb4;
USE `baili_test`;
CREATE TABLE `client_info`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `client_id`         varchar(60)         NOT NULL COMMENT '客户端ID',
    `client_type`       varchar(30)         NOT NULL COMMENT '客户端类型',
    `node_name`         varchar(60)         NOT NULL COMMENT '所在节点名称',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_client_id` (`client_id`),
    KEY `idx_node_name` (`node_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='客户端信息';