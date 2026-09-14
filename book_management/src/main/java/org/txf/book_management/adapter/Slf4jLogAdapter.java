package org.txf.book_management.adapter;

import org.springframework.stereotype.Component;

// Slf4j的日志适配器
@Component
public class Slf4jLogAdapter implements BasicLogAdapter{
    @Override
    public void log(String message) {
        MySlf4jLogger.printMessage(message);
    }
}
