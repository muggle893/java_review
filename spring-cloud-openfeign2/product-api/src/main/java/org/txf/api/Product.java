package org.txf.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private Long productPrice;
    private Integer state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}