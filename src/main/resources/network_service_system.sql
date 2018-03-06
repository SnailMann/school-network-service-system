# Host: localhost  (Version 5.6.26-log)
# Date: 2018-03-02 12:03:05
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "account_cancel"
#

DROP TABLE IF EXISTS `account_cancel`;
CREATE TABLE `account_cancel` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) DEFAULT '' COMMENT '账目订单号（学生、商店）',
  `student_id` varchar(255) DEFAULT NULL,
  `result_flag` int(11) DEFAULT '0' COMMENT '0是不作废，1是作废',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '作废原因（120字）',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0是不存在，1是存在',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

#
# Data for table "account_cancel"
#


#
# Structure for table "account_order_special"
#

DROP TABLE IF EXISTS `account_order_special`;
CREATE TABLE `account_order_special` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) NOT NULL DEFAULT '' COMMENT '特殊账目订单号',
  `money` double NOT NULL DEFAULT '0' COMMENT '金额',
  `worker_id` varchar(20) NOT NULL DEFAULT '' COMMENT '工号',
  `pirnt_time` date DEFAULT NULL COMMENT '打印时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='营帐管理-特殊账目';

#
# Data for table "account_order_special"
#


#
# Structure for table "login_role"
#

DROP TABLE IF EXISTS `login_role`;
CREATE TABLE `login_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL DEFAULT '' COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='登录模块-用户角色表';

#
# Data for table "login_role"
#

INSERT INTO `login_role` VALUES (1,'1','学生'),(2,'2','职工'),(3,'3','管理员');

#
# Structure for table "login_user"
#

DROP TABLE IF EXISTS `login_user`;
CREATE TABLE `login_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '用户登陆用id',
  `user_password` varchar(255) DEFAULT '' COMMENT '用户登陆用密码',
  `user_role_id` varchar(255) DEFAULT '1' COMMENT '用户角色id',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0为冻结，1为正常',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10174 DEFAULT CHARSET=utf8 COMMENT='登录模块-用户表';

#
# Data for table "login_user"
#

INSERT INTO `login_user` VALUES (1,'1001','123456','2',1),(2,'1002','123456','2',1),(3,'1003','123456','3',1);

#
# Structure for table "repair_record_student"
#

DROP TABLE IF EXISTS `repair_record_student`;
CREATE TABLE `repair_record_student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) NOT NULL DEFAULT '' COMMENT '学生学号',
  `add_time` datetime DEFAULT NULL COMMENT '录入时间',
  `contact_time` varchar(255) NOT NULL DEFAULT '' COMMENT '联系时间（文本）',
  `repair_content` varchar(255) NOT NULL DEFAULT '' COMMENT '报修内容（120字以内）',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0为不存在，1为存在',
  `result_flag` int(11) NOT NULL DEFAULT '0' COMMENT '0是为未处理，1是已处理',
  `advice` varchar(255) DEFAULT NULL COMMENT '已处理的意见（120字）',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='报修模块-报修记录';

#
# Data for table "repair_record_student"
#


#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT '' COMMENT '学生姓名',
  `student_idcard` varchar(20) DEFAULT '' COMMENT '学生身份证',
  `dorm` varchar(255) DEFAULT '' COMMENT '学生宿舍号',
  `phone` varchar(20) DEFAULT '' COMMENT '学生电话号码',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0是不存在，1为存在',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5979 DEFAULT CHARSET=utf8 COMMENT='通用实体-学生信息表';

#
# Data for table "student"
#


#
# Structure for table "student_openid"
#

DROP TABLE IF EXISTS `student_openid`;
CREATE TABLE `student_openid` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '学生登录id',
  `open_id` varchar(255) NOT NULL DEFAULT '',
  `flag` varchar(255) NOT NULL DEFAULT '1' COMMENT '0不存在，1存在',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `open_id` (`open_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信绑定学号';

#
# Data for table "student_openid"
#


#
# Structure for table "sys_charge_strategy"
#

DROP TABLE IF EXISTS `sys_charge_strategy`;
CREATE TABLE `sys_charge_strategy` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `speed` int(11) NOT NULL DEFAULT '0' COMMENT '宽带速率',
  `price` double NOT NULL DEFAULT '0' COMMENT '每个月多少钱',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0为不存在，1为存在',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='收费及系统管理模块-宽带收费策略';

#
# Data for table "sys_charge_strategy"
#


#
# Structure for table "useropen_broadband_student"
#

DROP TABLE IF EXISTS `useropen_broadband_student`;
CREATE TABLE `useropen_broadband_student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) NOT NULL DEFAULT '' COMMENT '学生学号',
  `student_access_number` varchar(255) DEFAULT '' COMMENT '宽带接入号',
  `student_accout_number` varchar(255) DEFAULT '' COMMENT '宽带账号',
  `network_speed` int(11) DEFAULT '0' COMMENT '宽带速率',
  `money` double DEFAULT '0' COMMENT '金额',
  `start_time` date DEFAULT NULL COMMENT '开通时间',
  `last_time` int(11) DEFAULT '0' COMMENT '持续时间（包多少个月）',
  `end_time` date DEFAULT NULL COMMENT '到期时间',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0为不存在，1为存在',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5979 DEFAULT CHARSET=utf8 COMMENT='用户开通模块-新生开通带宽信息';

#
# Data for table "useropen_broadband_student"
#


#
# Structure for table "useropen_order_student"
#

DROP TABLE IF EXISTS `useropen_order_student`;
CREATE TABLE `useropen_order_student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) NOT NULL DEFAULT '' COMMENT '学生学号',
  `name` varchar(255) DEFAULT '' COMMENT '学生名字',
  `dorm` varchar(255) DEFAULT '' COMMENT '房号',
  `password` varchar(255) DEFAULT '' COMMENT '初始密码',
  `username` varchar(20) DEFAULT '' COMMENT '账户',
  `last_time` int(11) DEFAULT '0' COMMENT '持续月份（包多少个月）',
  `network_speed` int(11) DEFAULT NULL COMMENT '网速',
  `money` double DEFAULT '0' COMMENT '开通金额',
  `print_time` date DEFAULT NULL COMMENT '打印时间',
  `end_time` date DEFAULT NULL COMMENT '到期时间',
  `order_id` varchar(20) DEFAULT '' COMMENT '订单号',
  `worker_id` varchar(20) DEFAULT '' COMMENT '工号',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0为不存在，1为存在',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='用户开通模块-学生订单表（交费记录）';

#
# Data for table "useropen_order_student"
#


#
# Structure for table "worker"
#

DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_id` varchar(20) NOT NULL DEFAULT '' COMMENT '工号',
  `name` varchar(255) DEFAULT '' COMMENT '工人姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '工人联系方式',
  `flag` int(11) NOT NULL DEFAULT '1' COMMENT '0为不存在，1为存在',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `worker_id` (`worker_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='通用实体-员工信息表';

#
# Data for table "worker"
#

INSERT INTO `worker` VALUES (1,'1001','1001','139',1),(2,'1002','1002','139',1),(3,'1003','admin','139',1);
