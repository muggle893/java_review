package org.txf.common.utils;

import java.util.HashMap;
import java.util.Map;

class JWTUtilsTest {

    @org.junit.jupiter.api.Test
    void genJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        String token = JWTUtils.genJwt(claims);
        System.out.println(token);
    }
}