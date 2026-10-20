package org.txf.api;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ParamTestInterface {
    @RequestMapping("/p1")
    public void testMultiParam(@RequestParam("id")int id, @RequestParam("name")String name);

    @RequestMapping("/p2")
    public void testObjectParam(@SpringQueryMap Product product);

    @RequestMapping("/p3")
    public void testJsonParam(@RequestBody Product product);
}
