package org.txf.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
//    -- 产品表
//    DROP TABLE IF EXISTS product_detail;
//    CREATE TABLE product_detail (
//`id` INT NOT NULL AUTO_INCREMENT COMMENT '产品id',
//`product_name` varchar ( 128 ) NULL COMMENT '产品名称',
//            `product_price` BIGINT ( 20 ) NOT NULL COMMENT '产品价格',
//            `state` TINYINT ( 4 ) NULL DEFAULT 0 COMMENT '产品状态0有效1下架',
//            `create_time` DATETIME DEFAULT now(),
//`update_time` DATETIME DEFAULT now(),
//    PRIMARY KEY ( id )) ENGINE = INNODB DEFAULT CHARACTER
//            SET = utf8mb4 COMMENT = '产品表';

    private Integer id;
    private String productName;
    private Long productPrice;
    private Integer state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
