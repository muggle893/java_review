package org.txf.book_management.adapter;

public class AdapterTest {
    public static void main(String[] args) {
        new Slf4jLogAdapter().log("hello");
        new LogbackAdapter().log("hello");
    }
}
