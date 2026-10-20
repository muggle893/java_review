package org.txf.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.txf.demo.model.Product;

@FeignClient(value = "product-service", path = "/product")
public interface ProductApi {
    @RequestMapping("/{productId}")
    Product getProductById(@PathVariable("productId") int id);
}
