USE `car_rental`;

TRUNCATE `tb_car`;
INSERT INTO `tb_car` (car_number, brand, model, type, price, deposit, store_name, status, description)
VALUES ('苏AT0001', '大众', '帕萨特', '轿车', '180', '500', '车库', '正常', '测试 1 号车'),
       ('苏AT0002', '大众', '宝来', '轿车', '130', '400', '车库', '正常', '测试 2 号车'),
       ('苏AT0003', '大众', '途观', 'SUV', '180', '500', '车库', '正常', '测试 3 号车');

TRUNCATE `sys_role`;
INSERT INTO `sys_role`(id, role_name, role_cn, description)
VALUES (1, 'superadmin', '超级管理员', '系统超级管理员，拥有所有超级权限'),
       (2, 'admin', '管理员', '管理员，系统维护人员用，仅次于超级管理员'),
       (3, 'salesman', '业务员', '业务员，业务人员用，维护客户信息、车辆信息、审核订单信息'),
       (4, 'troubleshooter', '故障处理专员', '故障处理专员，负责处理事故单，维修单'),
       (5, 'user', '用户', '用户');

TRUNCATE `tb_store`;
INSERT INTO `tb_store`(store_name, stock_limit)
VALUES ('公司总仓库', ~0 >> 40);

TRUNCATE `sys_permission`;
INSERT INTO `sys_permission`(id, permission_name, permission_cn, description)
VALUES (11, 'CreateOrder', '创建订单', null),
       (12, 'FindOrder', '查找订单', null),
       (13, 'ReviewOrder', '审核订单', null),
       (14, 'UpdateOrder', '修改订单', null),
       (15, 'DeleteOrder', '删除订单', null),

       (21, 'AddCar', '新增车辆', null),
       (22, 'UpdateCar', '修改车辆', null),
       (23, 'FindCar', '查找车辆', null),
       (24, 'DeleteCar', '删除车辆', null),

       (31, 'AddUser', '新增用户', null),
       (32, 'UpdateUser', '修改用户', null),
       (33, 'FindUser', '查找用户', null),
       (34, 'DeleteUser', '删除用户', null),
       (35, 'AddAdmin', '增加管理员', null),

       (41, 'AddRepair', '新增维修单', null),
       (42, 'UpdateRepair', '修改维修单', null),
       (43, 'FindRepair', '查找维修单', null),
       (44, 'DeleteRepair', '删除维修单', null),

       (51, 'AddAccident', '新增事故单', null),
       (52, 'UpdateAccident', '修改事故单', null),
       (53, 'FindAccident', '查找事故单', null),
       (54, 'DeleteAccident', '删除事故单', null);

TRUNCATE `sys_role_permission`;
INSERT INTO `sys_role_permission`(role_id, permission_id)
VALUES (1, 11),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15),

       (1, 21),
       (1, 22),
       (1, 23),
       (1, 24),

       (1, 31),
       (1, 32),
       (1, 33),
       (1, 34),
       (1, 35),

       (1, 41),
       (1, 42),
       (1, 43),
       (1, 44),

       (1, 51),
       (1, 52),
       (1, 53),
       (1, 54),

       (2, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (2, 15),

       (2, 21),
       (2, 22),
       (2, 23),
       (2, 24),

       (2, 31),
       (2, 32),
       (2, 33),
       (2, 34),
       (2, 35),

       (2, 41),
       (2, 42),
       (2, 43),
       (2, 44),

       (2, 51),
       (2, 52),
       (2, 53),
       (2, 54),

       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),

       (3, 21),
       (3, 22),
       (3, 23),

       (3, 31),
       (3, 33),

       (3, 41),
       (3, 42),
       (3, 43),

       (3, 51),
       (3, 52),
       (3, 53),

       (4, 12),

       (4, 22),
       (4, 23),

       (4, 33),

       (4, 41),
       (4, 42),
       (4, 43),

       (4, 51),
       (4, 52),
       (4, 53),

       (5, 11),
       (5, 12),

       (5, 23),

       (5, 31),
       (5, 32),
       (5, 34),

       (5, 41),
       (5, 42),

       (5, 51),
       (5, 52);

