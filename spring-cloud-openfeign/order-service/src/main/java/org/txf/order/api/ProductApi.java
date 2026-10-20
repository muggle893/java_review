package org.txf.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.txf.api.ProductInterface;

@FeignClient(value = "product-service", path = "/product", contextId = "productapi")
public interface ProductApi extends ProductInterface {
}
