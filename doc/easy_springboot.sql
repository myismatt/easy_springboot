/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.199.15-3124
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 192.168.199.15:3124
 Source Schema         : easy_springboot

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/07/2022 18:21:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for easy_config
-- ----------------------------
DROP TABLE IF EXISTS `easy_config`;
CREATE TABLE `easy_config`
(
    `id`           bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `config_name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '参数键值',
    `config_type`  int unsigned                                                  NOT NULL DEFAULT '0' COMMENT '系统内置（0否,1是 ）',
    `create_time`  timestamp                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time`  timestamp                                                     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `index_config_key` (`config_key`) USING BTREE COMMENT 'config_key唯一键值索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='参数配置';

-- ----------------------------
-- Records of easy_config
-- ----------------------------
BEGIN;
INSERT INTO `easy_config` (`id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_time`,
                           `update_time`, `remark`)
VALUES (1, '启用注册功能', 'enable_register', '0', 1, '2022-07-05 17:24:02', '2022-07-05 17:26:34', '');
INSERT INTO `easy_config` (`id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_time`,
                           `update_time`, `remark`)
VALUES (4, '登录超时时间', 'login_timeout', '280', 1, '2022-07-06 11:25:05', '2022-07-06 11:25:05', '登录超时时间/分钟');
COMMIT;

-- ----------------------------
-- Table structure for easy_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `easy_dict_data`;
CREATE TABLE `easy_dict_data`
(
    `id`           bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_type_id` bigint unsigned                                               NOT NULL COMMENT '字典类型ID',
    `dict_type`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
    `dict_sort`    int unsigned                                                           DEFAULT '0' COMMENT '字典排序',
    `dict_label`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '字典标签',
    `dict_value`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典键值',
    `css_class`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '表格回显样式',
    `is_default`   int unsigned                                                  NOT NULL DEFAULT '0' COMMENT '是否默认(0否,1-是)',
    `status`       int unsigned                                                  NOT NULL DEFAULT '0' COMMENT '状态(0-正常,1-停用)',
    `create_time`  timestamp                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time`  timestamp                                                     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_type_id` (`dict_type_id`) USING BTREE COMMENT '字典type关联索引',
    CONSTRAINT `easy_dict_data_ibfk_1` FOREIGN KEY (`dict_type_id`) REFERENCES `easy_dict_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='字典数据';

-- ----------------------------
-- Records of easy_dict_data
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for easy_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `easy_dict_type`;
CREATE TABLE `easy_dict_type`
(
    `id`          bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
    `status`      int unsigned                                                  NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_time` timestamp                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp                                                     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='字典类型';

-- ----------------------------
-- Records of easy_dict_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for easy_menu
-- ----------------------------
DROP TABLE IF EXISTS `easy_menu`;
CREATE TABLE `easy_menu`
(
    `id`          bigint unsigned                                              NOT NULL AUTO_INCREMENT COMMENT '主键',
    `menu_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint unsigned                                                   DEFAULT '0' COMMENT '父菜单ID',
    `order_num`   int                                                               DEFAULT '0' COMMENT '显示顺序',
    `path`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '组件路径',
    `is_frame`    int unsigned                                                      DEFAULT '0' COMMENT '是否为外链(0-否;1-是)\n',
    `menu_type`   int unsigned                                                      DEFAULT NULL COMMENT '菜单类型（0目录;1页面;2按钮）',
    `show`        int                                                               DEFAULT '0' COMMENT '菜单是否显示（0隐藏;1显示）',
    `enable`      int unsigned                                                      DEFAULT '1' COMMENT '菜单是否启用（0停用;1正常 ）',
    `perms`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT '#' COMMENT '菜单图标',
    `create_time` timestamp                                                    NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp                                                    NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='菜单路由';

-- ----------------------------
-- Records of easy_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for easy_role
-- ----------------------------
DROP TABLE IF EXISTS `easy_role`;
CREATE TABLE `easy_role`
(
    `id`          bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_name`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名字',
    `role_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色key',
    `role_sort`   int unsigned                                                  NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `status`      int unsigned                                                  NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
    `create_time` timestamp                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp                                                     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_role_key` (`role_key`) USING BTREE COMMENT '角色关键词唯一'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色';

-- ----------------------------
-- Records of easy_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for easy_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `easy_role_menu`;
CREATE TABLE `easy_role_menu`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色ID',
    `menu_id`     bigint unsigned NOT NULL COMMENT '菜单ID',
    `create_time` timestamp       NULL                                          DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp       NULL                                          DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `fk_role_id` (`role_id`),
    KEY `fk_menu_id` (`menu_id`),
    CONSTRAINT `easy_role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `easy_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `easy_role_menu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `easy_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色菜单关联';

-- ----------------------------
-- Records of easy_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for easy_user
-- ----------------------------
DROP TABLE IF EXISTS `easy_user`;
CREATE TABLE `easy_user`
(
    `id`             bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_key`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户特殊编码',
    `username`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '账号',
    `phone`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '手机号',
    `email`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '邮箱',
    `password`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `register_time`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
    `account_status` int unsigned                                                  NOT NULL COMMENT '账号状态',
    `create_time`    timestamp                                                     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_time`    timestamp                                                     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `indexs_username` (`username`) USING BTREE COMMENT '账号唯一索引',
    UNIQUE KEY `indexs_user_key` (`user_key`) USING BTREE COMMENT '唯一编码索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='用户';

-- ----------------------------
-- Records of easy_user
-- ----------------------------
BEGIN;
INSERT INTO `easy_user` (`id`, `user_key`, `username`, `phone`, `email`, `password`, `register_time`, `account_status`,
                         `create_time`, `update_time`, `remark`)
VALUES (2, 's9iKhc9W', 'admin', NULL, 'myismatt@foxmail.com',
        '$2a$10$r04nwF4ZFXrOILqSDW1DguIT/f.M7TKB.y5/w2TtNHkN.xwRdcJN.', '2022-06-24 17:38:47', 1, '2022-06-24 17:38:47',
        '2022-06-24 17:38:47', NULL);
COMMIT;

-- ----------------------------
-- Table structure for easy_user_info
-- ----------------------------
DROP TABLE IF EXISTS `easy_user_info`;
CREATE TABLE `easy_user_info`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户id',
    `nickname`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
    `create_time` timestamp       NULL                                          DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp       NULL                                          DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_user_id` (`user_id`) USING BTREE COMMENT '账号id关联',
    CONSTRAINT `easy_user_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `easy_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='用户详细数据';

-- ----------------------------
-- Records of easy_user_info
-- ----------------------------
BEGIN;
INSERT INTO `easy_user_info` (`id`, `user_id`, `nickname`, `create_time`, `update_time`, `remark`)
VALUES (2, 2, 'adminD4nVswfv', '2022-06-24 17:38:47', '2022-06-24 17:38:47', NULL);
COMMIT;

-- ----------------------------
-- Table structure for easy_user_role
-- ----------------------------
DROP TABLE IF EXISTS `easy_user_role`;
CREATE TABLE `easy_user_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户ID',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色ID',
    `create_time` timestamp       NULL                                          DEFAULT NULL COMMENT '创建时间',
    `update_time` timestamp       NULL                                          DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `fk_user_role_user_id` (`user_id`),
    KEY `fk_user_role_rloe_id` (`role_id`),
    CONSTRAINT `easy_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `easy_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `easy_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `easy_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户角色关联';

-- ----------------------------
-- Records of easy_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
