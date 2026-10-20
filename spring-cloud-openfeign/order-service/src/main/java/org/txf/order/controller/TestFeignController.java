package org.txf.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.api.Product;
import org.txf.order.api.ParamTestApi;

@RestController
@RequestMapping("/test")
public class TestFeignController {
    @Autowired
    private ParamTestApi paramTestApi;

    @RequestMapping("/o1")
    public void testMutltiParam() {
        int id = 1;
        String name = "zhangsan";
        paramTestApi.testMultiParam(id, name);
    }

    @RequestMapping("/o2")
    public void testObjectParam() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("测试传递一个对象参数");
        paramTestApi.testObjectParam(product);
    }

    @RequestMapping("/o3")
    public void testJsonParam() {
        Product product = new Product();
        product.setId(2);
        product.setProductName("测试传递一个对象参数");
        paramTestApi.testJsonParam(product);
    }
}
