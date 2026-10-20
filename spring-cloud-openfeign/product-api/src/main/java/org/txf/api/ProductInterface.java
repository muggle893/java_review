package org.txf.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ProductInterface {
    @RequestMapping("/{productId}")
    Product getProductById(@PathVariable("productId") int id);
}
