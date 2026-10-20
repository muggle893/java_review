package org.txf.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.txf.api.Product;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestFeignParameterController {

    // 测试用openfeign远程调用多个参数的情况
    @RequestMapping("/p1")
    public void testMultiParam(@RequestParam("id") int id, @RequestParam("name")String username) {
        log.info("product-service接收到多个参数。id:{},name:{}", id, username);
    }

    // 测试用openfeign远程调用传递一个对象的情况
    @RequestMapping("/p2")
    public void testObject(Product product) {
        log.info("product-service接收一个对象。" + product.toString());
    }

    // 测试用openfeign远程调用传递json数据的情况
    @RequestMapping("/p3")
    public void testJsonObject(@RequestBody Product product) {
        log.info("product-service接收到json字符串，并把json字符串转为一个对象:{}", product.toString());
    }
}
