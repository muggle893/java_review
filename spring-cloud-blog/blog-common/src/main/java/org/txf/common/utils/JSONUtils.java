package org.txf.common.utils;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JSONUtils {

    /**
     * 把对象转为json字符串
     * @param obj 转换的对象，如果对象为null，则返回null
     * @return 转换成功则返回对象的json字符串
     */
    public static String toJSON(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj);
    }


    /**
     *
     * @param jsonString 需要转换成对象的字符串
     * @param clazz     目标类型的类对象
     * @return          转换成功就返回对应的对象
     * @param <T>
     */
    public static <T> T toObject(String jsonString, Class<T> clazz) {
        if (jsonString == null || clazz == null || jsonString.length() == 0) {
            return null;
        }
        return JSON.parseObject(jsonString, clazz);
    }
}
