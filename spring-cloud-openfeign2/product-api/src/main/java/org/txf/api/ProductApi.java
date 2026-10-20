package org.txf.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "product-service", path = "/product", contextId = "productapi")
public interface ProductApi {
    @RequestMapping("/{productId}")
    Product getProductById(@PathVariable("productId") int id);
}
