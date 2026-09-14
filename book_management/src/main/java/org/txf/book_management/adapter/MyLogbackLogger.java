package org.txf.book_management.adapter;

import java.time.LocalDateTime;

public class MyLogbackLogger {
    public static void logMessage(String message) {
        System.out.println("Logback-" + LocalDateTime.now() + ": " + message);
    }
}
