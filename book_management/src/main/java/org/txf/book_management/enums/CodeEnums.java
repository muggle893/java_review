package org.txf.book_management.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeEnums {
    LOGIN_FAIL(1, "登录失败");
    private int code;
    private String msg;
}
