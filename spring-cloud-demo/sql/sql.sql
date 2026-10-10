create database if not exists cloud_order charset utf8mb4;

use cloud_order;
DROP TABLE IF EXISTS order_detail;
CREATE TABLE order_detail (
`id` INT NOT NULL AUTO_INCREMENT COMMENT '订单id',
`user_id` BIGINT ( 20 ) NOT NULL COMMENT '订单ID',
`product_id` BIGINT ( 20 ) NULL COMMENT '产品id',
`num` INT ( 10 ) NULL DEFAULT 0 COMMENT '下单数量',
`price` BIGINT ( 20 ) NOT NULL COMMENT '实付款',
`delete_flag` TINYINT ( 4 ) NULL DEFAULT 0,
`create_time` DATETIME DEFAULT now(),
`update_time` DATETIME DEFAULT now(),
PRIMARY KEY ( id )) ENGINE = INNODB DEFAULT CHARACTER 
SET = utf8mb4 COMMENT = '订单表';

-- 数据初始化
insert into order_detail (user_id,product_id,num,price)
values
(2001, 1001,1,99), (2002, 1002,1,30), (2001, 1003,1,40),
(2003, 1004,3,58), (2004, 1005,7,85), (2005, 1006,7,94);



create database if not exists cloud_product charset utf8mb4;
use cloud_product;
-- 产品表
DROP TABLE IF EXISTS product_detail;
CREATE TABLE product_detail (
`id` INT NOT NULL AUTO_INCREMENT COMMENT '产品id',
`product_name` varchar ( 128 ) NULL COMMENT '产品名称',
`product_price` BIGINT ( 20 ) NOT NULL COMMENT '产品价格',
`state` TINYINT ( 4 ) NULL DEFAULT 0 COMMENT '产品状态0有效1下架',
`create_time` DATETIME DEFAULT now(),
`update_time` DATETIME DEFAULT now(),
PRIMARY KEY ( id )) ENGINE = INNODB DEFAULT CHARACTER 
SET = utf8mb4 COMMENT = '产品表';

-- 数据初始化

insert into product_detail (id, product_name,product_price,state)
values
(1001,"T恤", 101, 0), (1002, "短袖",30, 0), (1003, "短裤",44, 0), 
(1004, "卫衣",58, 0), (1005, "马甲",98, 0),(1006,"羽绒服", 101, 0), (1007, "冲锋衣",30, 0), (1008, "袜子",44, 0), (1009, "鞋子",58, 0),(10010, "毛衣",98, 0)

