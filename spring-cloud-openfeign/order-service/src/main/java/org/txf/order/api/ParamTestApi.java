package org.txf.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.txf.api.ParamTestInterface;

@FeignClient(value = "product-service", path = "/test", contextId = "testapi")
public interface ParamTestApi extends ParamTestInterface {
}
