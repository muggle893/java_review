package org.txf.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.product.model.Product;
import org.txf.product.service.ProductService;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping({"/{prodId}"})
    public Product getProduct(@PathVariable("prodId") Integer prodId) {
        Assert.notNull(prodId, "prodId must not be null");
        log.info("Get product by id: {}", prodId);
        return productService.getProductById(prodId);
    }
}
