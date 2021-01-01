/*
 Navicat Premium Data Transfer

 Source Server         : mysql_conn
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : ib_system

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 17/06/2020 17:40:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of bus
-- ----------------------------
INSERT INTO `bus` VALUES (1, '川A-23233', '挂接巴士', '启用', '2020-04-01');
INSERT INTO `bus` VALUES (2, '鄂A-12112', '挂接巴士', '停运', '2019-12-18');
INSERT INTO `bus` VALUES (3, '鄂B-89899', '房车型', '启用', '2019-12-11');
INSERT INTO `bus` VALUES (4, '鄂B-421A1', '双层豪华大巴', '停运', '2019-08-20');
INSERT INTO `bus` VALUES (7, '鄂B-452D1', '中高型大巴', '启用', '2020-06-11');
INSERT INTO `bus` VALUES (9, '鄂A-23D23', '高大型', '停运', '2020-06-10');
INSERT INTO `bus` VALUES (10, '鄂A-14526', '火车型', '启用', '2020-06-10');
INSERT INTO `bus` VALUES (15, '鄂A-12452', 'C919型', '启用', '2020-06-10');

-- ----------------------------
-- Records of line
-- ----------------------------
INSERT INTO `line` VALUES (1, '绕城线', '启用', '2020-06-08', '下行');
INSERT INTO `line` VALUES (3, '穿城线', '启用', '2020-04-29', '上行');
INSERT INTO `line` VALUES (4, '三八线', '停运', '2020-06-05', '下行');
INSERT INTO `line` VALUES (5, '马甲线', '启用', '2020-06-03', '上行');
INSERT INTO `line` VALUES (8, '秦岭淮河一线', '停运', '2020-06-25', '下行');
INSERT INTO `line` VALUES (9, '亚欧大陆分界线', '停运', '2020-06-10', '上行');
INSERT INTO `line` VALUES (14, '和平线', '停运', '2020-06-11', '上行');
INSERT INTO `line` VALUES (16, '平安线', '启用', '2020-06-04', '下行');

-- ----------------------------
-- Records of line_station
-- ----------------------------
INSERT INTO `line_station` VALUES (188, 8, 6);
INSERT INTO `line_station` VALUES (189, 8, 9);
INSERT INTO `line_station` VALUES (206, 4, 11);
INSERT INTO `line_station` VALUES (207, 4, 4);
INSERT INTO `line_station` VALUES (208, 4, 2);
INSERT INTO `line_station` VALUES (221, 3, 7);
INSERT INTO `line_station` VALUES (222, 3, 1);
INSERT INTO `line_station` VALUES (223, 3, 6);
INSERT INTO `line_station` VALUES (224, 3, 13);
INSERT INTO `line_station` VALUES (225, 3, 9);
INSERT INTO `line_station` VALUES (226, 3, 2);
INSERT INTO `line_station` VALUES (227, 5, 13);
INSERT INTO `line_station` VALUES (228, 5, 2);
INSERT INTO `line_station` VALUES (229, 5, 1);
INSERT INTO `line_station` VALUES (233, 1, 11);
INSERT INTO `line_station` VALUES (234, 1, 2);
INSERT INTO `line_station` VALUES (235, 1, 10);
INSERT INTO `line_station` VALUES (257, 9, 5);
INSERT INTO `line_station` VALUES (258, 9, 3);
INSERT INTO `line_station` VALUES (259, 9, 2);
INSERT INTO `line_station` VALUES (266, 14, 1);
INSERT INTO `line_station` VALUES (267, 14, 6);
INSERT INTO `line_station` VALUES (268, 14, 9);
INSERT INTO `line_station` VALUES (269, 14, 13);
INSERT INTO `line_station` VALUES (270, 14, 7);
INSERT INTO `line_station` VALUES (271, 14, 2);
INSERT INTO `line_station` VALUES (272, 16, 11);
INSERT INTO `line_station` VALUES (273, 16, 3);
INSERT INTO `line_station` VALUES (274, 16, 2);

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'bus_home_menu', '车辆信息管理');
INSERT INTO `permission` VALUES (2, 'station_home_menu', '站点信息管理');
INSERT INTO `permission` VALUES (3, 'line_home_menu', '线路信息管理');
INSERT INTO `permission` VALUES (4, 'scheduling_home_menu', '排班信息管理');
INSERT INTO `permission` VALUES (5, 'user_home_menu', '员工信息管理');
INSERT INTO `permission` VALUES (6, 'user_change_pass_home_menu', '修改密码');
INSERT INTO `permission` VALUES (7, 'user_add', '添加用户');
INSERT INTO `permission` VALUES (8, 'user_del', '删除用户');
INSERT INTO `permission` VALUES (9, 'user_save', '保存用户');
INSERT INTO `permission` VALUES (10, 'scheduling_add', '增加排班');
INSERT INTO `permission` VALUES (11, 'scheduling_del', '删除排班');
INSERT INTO `permission` VALUES (12, 'scheduling_save', '保存排班');
INSERT INTO `permission` VALUES (13, 'bus_add', '添加车辆');
INSERT INTO `permission` VALUES (14, 'bus_del', '删除车辆');
INSERT INTO `permission` VALUES (15, 'bus_save', '保存车辆信息');
INSERT INTO `permission` VALUES (16, 'station_add', '添加站点');
INSERT INTO `permission` VALUES (17, 'station_del', '删除站点');
INSERT INTO `permission` VALUES (18, 'station_save', '保存站点');
INSERT INTO `permission` VALUES (19, 'line_add', '增加线路');
INSERT INTO `permission` VALUES (20, 'line_del', '删除线路');
INSERT INTO `permission` VALUES (21, 'line_save', '保存线路');
INSERT INTO `permission` VALUES (22, 'base_data', '进入基础数据');
INSERT INTO `permission` VALUES (23, 'scheduling', '进入排班管理');
INSERT INTO `permission` VALUES (24, 'user_manager', '进入用户管理');

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '[驾驶员]', '负责驾驶车辆');
INSERT INTO `role` VALUES (2, '[管理员]', '管理整个系统');
INSERT INTO `role` VALUES (3, '[调度员]', '负责排班');

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 2);
INSERT INTO `role_permission` VALUES (2, 2, 2);
INSERT INTO `role_permission` VALUES (3, 3, 2);
INSERT INTO `role_permission` VALUES (4, 4, 2);
INSERT INTO `role_permission` VALUES (5, 5, 2);
INSERT INTO `role_permission` VALUES (6, 6, 2);
INSERT INTO `role_permission` VALUES (7, 4, 1);
INSERT INTO `role_permission` VALUES (8, 6, 1);
INSERT INTO `role_permission` VALUES (9, 1, 3);
INSERT INTO `role_permission` VALUES (10, 2, 3);
INSERT INTO `role_permission` VALUES (11, 3, 3);
INSERT INTO `role_permission` VALUES (12, 4, 3);
INSERT INTO `role_permission` VALUES (13, 5, 3);
INSERT INTO `role_permission` VALUES (14, 6, 3);
INSERT INTO `role_permission` VALUES (15, 7, 2);
INSERT INTO `role_permission` VALUES (16, 8, 2);
INSERT INTO `role_permission` VALUES (17, 9, 2);
INSERT INTO `role_permission` VALUES (18, 10, 2);
INSERT INTO `role_permission` VALUES (19, 11, 2);
INSERT INTO `role_permission` VALUES (20, 12, 2);
INSERT INTO `role_permission` VALUES (21, 10, 3);
INSERT INTO `role_permission` VALUES (22, 11, 3);
INSERT INTO `role_permission` VALUES (23, 12, 3);
INSERT INTO `role_permission` VALUES (24, 13, 2);
INSERT INTO `role_permission` VALUES (25, 14, 2);
INSERT INTO `role_permission` VALUES (26, 15, 2);
INSERT INTO `role_permission` VALUES (27, 16, 2);
INSERT INTO `role_permission` VALUES (28, 17, 2);
INSERT INTO `role_permission` VALUES (29, 18, 2);
INSERT INTO `role_permission` VALUES (30, 19, 2);
INSERT INTO `role_permission` VALUES (31, 20, 2);
INSERT INTO `role_permission` VALUES (32, 21, 2);
INSERT INTO `role_permission` VALUES (33, 22, 2);
INSERT INTO `role_permission` VALUES (34, 23, 2);
INSERT INTO `role_permission` VALUES (35, 23, 3);
INSERT INTO `role_permission` VALUES (36, 24, 2);


-- ----------------------------
-- Records of scheduling
-- ----------------------------
INSERT INTO `scheduling` VALUES (1, 1, '川A-23233', '2', '5', 1, 1, 3);
INSERT INTO `scheduling` VALUES (2, 5, '鄂B-89899', '5', '10', 5, 5, 2);
INSERT INTO `scheduling` VALUES (4, 3, '鄂B-452D1', '5', '20', 4, 2, 7);
INSERT INTO `scheduling` VALUES (5, 3, '鄂B-89899', '15', '20', 8, 2, 7);
INSERT INTO `scheduling` VALUES (9, 5, '鄂B-452D1', '4', '255', 8, 11, 7);
INSERT INTO `scheduling` VALUES (11, 9, '鄂B-452D1', '45', '14', 2, 7, 2);
INSERT INTO `scheduling` VALUES (13, 3, '川A-23233', '12', '15', 5, 2, 5);

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES (1, '湖师站', '122.53364', '38.45123', '黄石港区', '磁湖路');
INSERT INTO `station` VALUES (2, '万达广场站', '122.42635', '38.48512', '黄石港区', '万达路');
INSERT INTO `station` VALUES (3, '仙岛湖站', '122.25146', '38.15426', '阳新县王英镇', '仙岛路');
INSERT INTO `station` VALUES (4, '国家矿山公园站', '122.45321', '38.46215', '铁山区', '矿山路');
INSERT INTO `station` VALUES (5, '青龙山社区站', '122.42136', '38.49513', '下陆区', '青龙路');
INSERT INTO `station` VALUES (6, '沈家营站', '122.45216', '38.45216', '黄石港区', '磁湖路');
INSERT INTO `station` VALUES (7, '南岳村站', '122.1546', '38.46125', '黄石港区', '磁湖路');
INSERT INTO `station` VALUES (9, '中英文学校站', '122.1462', '38.46216', '黄石港区', '学校路');
INSERT INTO `station` VALUES (10, '张之洞站', '122.14623', '38.48136', '下陆区', '矿山路');
INSERT INTO `station` VALUES (11, '青山湖站', '122.1524', '38.15411', ' 黄石港区', ' 磁湖路');
INSERT INTO `station` VALUES (13, '理工站', '122.15424', '38.45122', '黄石港区', '磁湖路');
INSERT INTO `station` VALUES (14, '毕业站', '122.15421', '38.45123', '黄石港区', '磁湖路');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 2, 'zhangsan', '20917c851c4a54f2a054390dac9085b7', '喻清明', '17665457395', '511023199902283879', 2, '启用');
INSERT INTO `user` VALUES (2, 3, 'liliang', '3cf0215457191ddeb37484463a790117', '彭洪洋', '18398027395', '511021200012453650', 1, '禁用');
INSERT INTO `user` VALUES (4, 3, 'pengweiwei', '546677201aae8c8cb69893a4a30d4464', '彭维维', '18545251635', '511026199804156351', 0, '启用');
INSERT INTO `user` VALUES (5, 3, 'tangjianglong', '97fdc24b87013b89246a195d68195ae1', '唐江龙', '13514142635', '511024200212453695', 0, '启用');
INSERT INTO `user` VALUES (6, 1, 'yuhang', 'e10adc3949ba59abbe56e057f20f883e', '喻航', '15183240451', '511023199703051653', 6, '禁用');
INSERT INTO `user` VALUES (8, 3, 'nihao', 'e10adc3949ba59abbe56e057f20f883e', '杨杰', '13548723614', '415263199203124525', 5, '启用');
INSERT INTO `user` VALUES (10, 2, 'xiaojue', 'e10adc3949ba59abbe56e057f20f883e', '李向东', '18526354951', '413263199605043625', 4, '启用');

