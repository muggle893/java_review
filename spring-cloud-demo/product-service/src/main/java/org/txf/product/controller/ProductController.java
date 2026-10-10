package org.txf.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.product.model.Product;
import org.txf.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping({"/{prodId}"})
    public Product getProduct(@PathVariable("prodId") Integer prodId) {
        Assert.notNull(prodId, "prodId must not be null");
        return productService.getProductById(prodId);
    }
}
