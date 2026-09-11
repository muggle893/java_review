package org.txf.book_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
// lombok注解生成对应的getter, setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private Double price;
    private String publish;
    private Integer status;
    private String statusCN;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
