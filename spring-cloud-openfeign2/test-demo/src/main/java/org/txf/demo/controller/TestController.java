package org.txf.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.demo.api.ProductApi;
import org.txf.demo.model.Product;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ProductApi productApi;

    @RequestMapping("/1")
    public String test1() {
        // 远程调用product服务
        Product product = productApi.getProductById(1001);
        return product.toString();
    }
}
