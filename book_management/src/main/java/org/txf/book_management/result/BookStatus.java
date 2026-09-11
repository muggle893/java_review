package org.txf.book_management.result;

public enum BookStatus {

    // 枚举常量必须放在最前面
    INVALID(0, "无效"),
    NORMAL(1, "可借阅"),
    NOT_BORROWABLE(2, "不可借阅");

    // 每个状态对应的数字和文字
    private final int code;
    private final String description;

    // 构造方法，为上面的枚举常量赋值
    private BookStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 根据数据库中的数字查找对应枚举
    public static BookStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (BookStatus status : BookStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        // 遇到没有定义的状态码
        return null;
    }
}
