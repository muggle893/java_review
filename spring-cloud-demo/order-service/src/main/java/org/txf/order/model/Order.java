package org.txf.order.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
//    CREATE TABLE order_detail (
//`id` INT NOT NULL AUTO_INCREMENT COMMENT '订单id',
//`user_id` BIGINT ( 20 ) NOT NULL COMMENT '订单ID',
//            `product_id` BIGINT ( 20 ) NULL COMMENT '产品id',
//            `num` INT ( 10 ) NULL DEFAULT 0 COMMENT '下单数量',
//            `price` BIGINT ( 20 ) NOT NULL COMMENT '实付款',
//            `delete_flag` TINYINT ( 4 ) NULL DEFAULT 0,
//            `create_time` DATETIME DEFAULT now(),
//`update_time` DATETIME DEFAULT now(),
//    PRIMARY KEY ( id )) ENGINE = INNODB DEFAULT CHARACTER
//            SET = utf8mb4 COMMENT = '订单表';
    private Integer id;
    private Long userId;
    private Long productId;
    private Integer num;
    private Long price;
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Product product;
}
