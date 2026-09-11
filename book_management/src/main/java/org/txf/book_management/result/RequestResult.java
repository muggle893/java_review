package org.txf.book_management.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 这是接口统一返回对象
// code: 状态码
// msg: 状态码对应的意义
// data: 返回的数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResult<T> {
    private Integer code;
    private String message;
    private T data;

    // 成功的时候返回的对象
    public static <T> RequestResult<T> success() {
        RequestResult<T> ret = new RequestResult<>();
        ret.setCode(200);
        ret.setMessage("success");
        return ret;
    }

    // 成功的时候返回的对象
    public static <T> RequestResult<T> success(String message) {
        RequestResult<T> ret = new RequestResult<>();
        ret.setCode(200);
        ret.setMessage(message);
        return ret;
    }

    // 失败的时候返回的对象
    public static <T> RequestResult<T> fail(String message) {
        RequestResult<T> ret = new RequestResult<>();
        ret.setCode(-1);
        ret.setMessage(message);
        return ret;
    }

    // 成功的时候返回的对象（有数据）
    public static <T> RequestResult<T> success(T data) {
        RequestResult<T> ret = new RequestResult<>();
        ret.setCode(200);
        ret.setMessage("success");
        ret.setData(data);
        return ret;
    }
}
