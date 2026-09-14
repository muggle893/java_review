package org.txf.book_management.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestErrorAdvice {

    @RequestMapping("/er1")
    public boolean testArithmeticError() {
        return 10 / 0 == 0;
    }

    public void testRE() {
        throw new RuntimeException("testRE");
    }

    public boolean testRuntimeError() {
        testRE();
        return true;
    }

    public boolean testExceptionError() {
        throw new IllegalStateException("testExceptionError");
    }
}
