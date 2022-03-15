DROP DATABASE IF EXISTS `car_rental`;
CREATE DATABASE `car_rental` DEFAULT CHARACTER SET utf8mb4;
USE `car_rental`;

DROP TABLE IF EXISTS `tb_car`;
CREATE TABLE `tb_car`
(
    id          BIGINT(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '车辆 ID',
    car_number  VARCHAR(255)        NOT NULL COMMENT '车牌号',
    brand       VARCHAR(255)        NOT NULL COMMENT '品牌',
    model       VARCHAR(255)        NOT NULL COMMENT '型号',
    type        VARCHAR(255)        NOT NULL COMMENT '车型',
    price       DOUBLE(10, 2)       NULL COMMENT '租金/天',
    deposit     DOUBLE(10, 2)       NULL COMMENT '定金',
    store_name  VARCHAR(255)        NULL COMMENT '当前所在门店名称',
    img         VARCHAR(255)        NULL COMMENT '车辆照片',
    status      VARCHAR(255)        NULL COMMENT '车辆当前状态',
    description VARCHAR(255)        NULL COMMENT '备注',
    create_time TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted  TINYINT(1)          NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    is_usable   TINYINT(1)          NULL DEFAULT 0 COMMENT '可用标记,0 为可用,1 为不可用',
    PRIMARY KEY pk_car_id (id) USING BTREE COMMENT '车辆 ID 主键',
    UNIQUE INDEX idx_car_num (car_number) USING BTREE COMMENT '车牌号唯一索引'
)
    COMMENT '车辆表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_store`;
CREATE TABLE `tb_store`
(
    id          BIGINT(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '门店 ID',
    store_name  VARCHAR(255)        NOT NULL COMMENT '门店名称',
    address     VARCHAR(255)        NULL COMMENT '门店地址',
    stock_limit INTEGER UNSIGNED    NULL COMMENT '门店车辆存放上限',
    stock_now   INTEGER UNSIGNED    NULL DEFAULT 0 COMMENT '当前车辆存放数量',
    stock_last  INTEGER UNSIGNED    NULL DEFAULT (stock_limit - stock_now) COMMENT '当前可用存放量',
    manager_id  BIGINT(12) UNSIGNED NULL COMMENT '店长 ID',
    description VARCHAR(255)        NULL COMMENT '备注',
    create_time TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted  TINYINT(1)          NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    is_disable  TINYINT(1)          NULL DEFAULT 0 COMMENT '禁用标记,0 代表未禁用,1 代表已禁用',
    PRIMARY KEY pk_user_id (id) USING BTREE COMMENT '门店 ID 主键'
)
    COMMENT '门店表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`
(
    id           BIGINT(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
    username     VARCHAR(255)        NOT NULL COMMENT '用户名',
    nickname     VARCHAR(255)        NOT NULL COMMENT '用户昵称',
    password     VARCHAR(255)        NOT NULL COMMENT '用户密码',
    real_name    VARCHAR(255)        NULL COMMENT '真实姓名',
    sex          VARCHAR(10)         NULL COMMENT '性别',
    phone_number VARCHAR(255)        NULL COMMENT '手机号码',
    email        VARCHAR(255)        NULL COMMENT '邮箱',
    description  VARCHAR(255)        NULL COMMENT '备注',
    create_time  TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time  TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted   TINYINT(1)          NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    is_disable   TINYINT(1)          NULL DEFAULT 0 COMMENT '禁用标记,0 代表未禁用,1 代表已禁用',
    PRIMARY KEY pk_user_id (id) USING BTREE COMMENT '用户 ID 主键',
    UNIQUE INDEX idx_username (username) USING BTREE COMMENT '用户名唯一索引',
    UNIQUE INDEX idx_nickname (nickname) USING BTREE COMMENT '用户昵称唯一索引',
    UNIQUE INDEX idx_phone_number (phone_number) USING BTREE COMMENT '手机号码唯一索引',
    UNIQUE INDEX idx_email (email) USING BTREE COMMENT '邮箱唯一索引'
)
    COMMENT '用户表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`
(
    id          BIGINT(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单 ID',
    order_num   VARCHAR(17)        NOT NULL UNIQUE COMMENT '订单编号',
    user_id     BIGINT(12) UNSIGNED NOT NULL COMMENT '用户 ID',
    car_id      BIGINT(12) UNSIGNED NOT NULL COMMENT '车辆 ID',
    operator_id BIGINT(12) UNSIGNED NULL COMMENT '操作员 ID',
    start_time  TIMESTAMP           NOT NULL COMMENT '开始时间',
    finish_time TIMESTAMP           NULL COMMENT '结束时间',
    total_price DOUBLE(10, 2)       NULL COMMENT '订单总价',
    description VARCHAR(255)        NULL COMMENT '备注',
    create_time TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted  TINYINT(1)          NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_order_id (id) USING BTREE COMMENT '订单 ID 主键'
)
    COMMENT '订单表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_repair`;
CREATE TABLE `tb_repair`
(
    id          BIGINT(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '维修单 ID',
    repair_num  VARCHAR(17)        NOT NULL UNIQUE COMMENT '维修单编号',
    user_id     BIGINT(12) UNSIGNED NOT NULL COMMENT '用户 ID',
    car_id      BIGINT(12) UNSIGNED NOT NULL COMMENT '车辆 ID',
    operator_id BIGINT(12) UNSIGNED NOT NULL COMMENT '操作员 ID',
    status      VARCHAR(255)        NULL COMMENT '当前状态',
    description VARCHAR(255)        NULL COMMENT '备注',
    create_time TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted  TINYINT(1)          NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_order_id (id) USING BTREE COMMENT '维修单 ID 主键'
)
    COMMENT '维修单表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tb_accident`;
CREATE TABLE `tb_accident`
(
    id           BIGINT(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '事故单 ID',
    accident_num VARCHAR(17)        NOT NULL UNIQUE COMMENT '事故单编号',
    user_id      BIGINT(12) UNSIGNED NOT NULL COMMENT '用户 ID',
    car_id       BIGINT(12) UNSIGNED NOT NULL COMMENT '车辆 ID',
    operator_id  BIGINT(12) UNSIGNED NOT NULL COMMENT '操作员 ID',
    status       VARCHAR(255)        NULL COMMENT '当前状态',
    description  VARCHAR(255)        NULL COMMENT '备注',
    create_time  TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time  TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted   TINYINT(1)          NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_order_id (id) USING BTREE COMMENT '事故单 ID 主键'
)
    COMMENT '事故单表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    id          INTEGER      NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
    role_name   VARCHAR(255) NOT NULL UNIQUE COMMENT '角色名称',
    role_cn     VARCHAR(255) NOT NULL UNIQUE COMMENT '中文名称',
    description VARCHAR(255) NULL COMMENT '备注',
    create_time TIMESTAMP    NULL DEFAULT NOW() COMMENT '创建时间',
    update_time TIMESTAMP    NULL COMMENT '更新时间',
    is_deleted  TINYINT(1)   NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_role_id (id) USING BTREE COMMENT '角色 ID 主键'
)
    COMMENT '角色表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    id              INTEGER      NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
    permission_name VARCHAR(255) NOT NULL UNIQUE COMMENT '权限名称',
    permission_cn   VARCHAR(255) NOT NULL UNIQUE COMMENT '中文名称',
    description     VARCHAR(255) NULL COMMENT '备注',
    create_time     TIMESTAMP    NULL DEFAULT NOW() COMMENT '创建时间',
    update_time     TIMESTAMP    NULL COMMENT '更新时间',
    is_deleted      TINYINT(1)   NULL DEFAULT 0 COMMENT '删除标记,0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_permission_id (id) USING BTREE COMMENT '权限 ID 主键'
)
    COMMENT '权限表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    id          INTEGER             NOT NULL AUTO_INCREMENT COMMENT '用户_角色表 ID',
    user_id     BIGINT(12) UNSIGNED NOT NULL COMMENT '用户 ID',
    role_id     INTEGER             NOT NULL COMMENT '角色 ID',
    create_time TIMESTAMP           NULL DEFAULT NOW() COMMENT '创建时间',
    update_time TIMESTAMP           NULL COMMENT '更新时间',
    is_deleted  TINYINT(1)          NULL DEFAULT 0 COMMENT '0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_user_role_id (id) USING BTREE COMMENT '用户_角色表 ID 主键',
    UNIQUE INDEX uni_idx_user_id (user_id) USING BTREE COMMENT '用户_角色表 用户 ID 索引',
    INDEX idx_role_id (role_id) USING BTREE COMMENT '用户_角色表 角色 ID 索引'
)
    COMMENT '用户_角色表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    id            INTEGER    NOT NULL AUTO_INCREMENT COMMENT '角色_权限表',
    role_id       INTEGER    NOT NULL COMMENT '角色 ID',
    permission_id INTEGER    NOT NULL COMMENT '权限 ID',
    create_time   TIMESTAMP  NULL DEFAULT NOW() COMMENT '创建时间',
    update_time   TIMESTAMP  NULL COMMENT '更新时间',
    is_deleted    TINYINT(1) NULL DEFAULT 0 COMMENT '0 代表未删除,1 代表已删除',
    PRIMARY KEY pk_role_permission_id (id) USING BTREE COMMENT '角色_权限表 ID 主键',
    INDEX idx_role_id (role_id) USING BTREE COMMENT '角色_权限表 角色 ID 索引',
    INDEX idx_permission_id (permission_id) USING BTREE COMMENT '角色_权限表 权限 ID 索引'
)
    COMMENT '角色_权限表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

DROP VIEW IF EXISTS `vw_userinfo`;
CREATE VIEW `vw_userinfo` AS
SELECT tu.*,
       sr.role_name,
       sr.role_cn
FROM `tb_user` tu
         LEFT JOIN `sys_user_role` sur on tu.id = sur.user_id
         LEFT JOIN `sys_role` sr on sur.role_id = sr.id;

DROP TRIGGER IF EXISTS `generateOrderNum`;
CREATE TRIGGER `generateOrderNum`
    BEFORE INSERT
    ON `tb_order`
    FOR EACH ROW
BEGIN
    DECLARE currentDate VARCHAR(8);
    DECLARE lastNum BIGINT UNSIGNED DEFAULT 0;
    DECLARE ORD_NUM VARCHAR(17);
    SELECT DATE_FORMAT(NOW(), '%Y%m%d') INTO currentDate;

    /*查找当日最后一个订单编号*/
    SELECT IFNULL(`order_num`, 'notnull')
    INTO ORD_NUM
    FROM `tb_order`
    WHERE SUBSTRING(`order_num`, 1, 3) = 'ORD'
      AND SUBSTRING(`order_num`, 4, 8) = currentDate
    ORDER BY id DESC
    LIMIT 1;

    IF ORD_NUM != '' THEN
        SET lastNum = CONVERT(SUBSTRING(order_num, -6), DECIMAL);
        SELECT CONCAT('ORD', currentDate, LPAD((lastNum + 1), 6, '0'))
        INTO ORD_NUM;
    ELSE
        SELECT CONCAT('ORD', currentDate, LPAD((lastNum + 1), 6, '0'))
        INTO ORD_NUM;
    END IF;

    SET NEW.`order_num`=ORD_NUM;
END;

DROP TRIGGER IF EXISTS `generateRepairNum`;
CREATE TRIGGER `generateRepairNum`
    BEFORE INSERT
    ON `tb_repair`
    FOR EACH ROW
BEGIN
    DECLARE currentDate VARCHAR(8);
    DECLARE lastNum BIGINT UNSIGNED DEFAULT 0;
    DECLARE REP_NUM VARCHAR(17);
    SELECT DATE_FORMAT(NOW(), '%Y%m%d') INTO currentDate;

    /*查找当日最后一个维修单编号*/
    SELECT IFNULL(`repair_num`, 'notnull')
    INTO REP_NUM
    FROM `tb_order`
    WHERE SUBSTRING(`repair_num`, 1, 3) = 'REP'
      AND SUBSTRING(`repair_num`, 4, 8) = currentDate
    ORDER BY id DESC
    LIMIT 1;

    IF REP_NUM != '' THEN
        SET lastNum = CONVERT(SUBSTRING(repair_num, -6), DECIMAL);
        SELECT CONCAT('REP', currentDate, LPAD((lastNum + 1), 6, '0'))
        INTO REP_NUM;
    ELSE
        SELECT CONCAT('REP', currentDate, LPAD((lastNum + 1), 6, '0'))
        INTO REP_NUM;
    END IF;

    SET NEW.`repair_num`=REP_NUM;
END;

DROP TRIGGER IF EXISTS `generateAccidentNum`;
CREATE TRIGGER `generateAccidentNum`
    BEFORE INSERT
    ON `tb_accident`
    FOR EACH ROW
BEGIN
    DECLARE currentDate VARCHAR(8);
    DECLARE lastNum BIGINT UNSIGNED DEFAULT 0;
    DECLARE ACCIDENT_NUM VARCHAR(17);
    SELECT DATE_FORMAT(NOW(), '%Y%m%d') INTO currentDate;

    /*查找当日最后一个维修单编号*/
    SELECT IFNULL(`accident_num`, 'notnull')
    INTO ACCIDENT_NUM
    FROM `tb_order`
    WHERE SUBSTRING(`accident_num`, 1, 3) = 'ACC'
      AND SUBSTRING(`accident_num`, 4, 8) = currentDate
    ORDER BY id DESC
    LIMIT 1;

    IF ACCIDENT_NUM != '' THEN
        SET lastNum = CONVERT(SUBSTRING(accident_num, -6), DECIMAL);
        SELECT CONCAT('ACC', currentDate, LPAD((lastNum + 1), 6, '0'))
        INTO ACCIDENT_NUM;
    ELSE
        SELECT CONCAT('ACC', currentDate, LPAD((lastNum + 1), 6, '0'))
        INTO ACCIDENT_NUM;
    END IF;

    SET NEW.`accident_num`=ACCIDENT_NUM;
END;