package org.txf.book_management.adapter;

import org.springframework.stereotype.Component;

@Component
public class LogbackAdapter implements BasicLogAdapter{
    public void log(String msg){
        MyLogbackLogger.logMessage(msg);
    }
}
