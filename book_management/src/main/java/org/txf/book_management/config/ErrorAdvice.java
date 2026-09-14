package org.txf.book_management.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.txf.book_management.result.RequestResult;

@RestControllerAdvice
@Slf4j
// 异常可以自己定义，比如参数异常，登录的时候参数异常就可以抛一个参数异常，
public class ErrorAdvice{

    // 算数异常处理
    @ExceptionHandler()
    public RequestResult handleArithmeticException(ArithmeticException ex){
        // 打印日志
        log.error("发生算数异常!!!");
        return RequestResult.failWithException(ex.getMessage());
    }

    // 运行时异常处理
    public RequestResult handleRunTimeException(ArithmeticException ex){
        // 打印日志
        log.error("发生运行时异常!");
        return RequestResult.failWithException(ex.getMessage());
    }

    // 运行时异常处理
    public RequestResult handleRunTimeException(Exception ex){
        // 打印日志
        log.error("发生异常!");
        return RequestResult.failWithException(ex.getMessage());
    }
}
