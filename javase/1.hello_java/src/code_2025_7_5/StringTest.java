package code_2025_7_5;

import java.util.Arrays;

/***
 * 这是一个用来熟悉String方法的类
 */
public class StringTest {
    public static void main(String[] args) {
        String str1 = new String("abc.abc.def.ggghhhh");
        String str2 = new String("  al;hgalg  ");

        // equals()方法来比较两个字符串中的内容是否相等
        System.out.println(str1.equals(str2));
        // compareTo，s1 > s2 返回大于0， s1 < s2返回小于0，s1等于s2返回等于0
        System.out.println(str1.compareTo(str2));

        // 获取子串, substring的参数左闭右开
        System.out.println(str1.substring(0, 3));
        System.out.println(str1.substring(3));
        // repalce替换子串
        System.out.println(str1.replace("abc", "aaa"));
        System.out.println(str1.replace('h', 'z'));

        // 转大小写
        System.out.println(str1.toLowerCase());
        System.out.println(str1.toUpperCase());

        // trim删除前后空格
        System.out.println(str2.trim());

        // 分割
        String[] split = str1.split("\\."); // .在正则表达式中是特殊字符要转义
        System.out.println(split[1]);
    }
}
