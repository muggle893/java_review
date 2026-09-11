package org.txf.book_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private String password;
    private int deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
