package org.txf.alibaba.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestBeanController {
    @Autowired
    private DashScopeChatModel dashScopeChatModel;
    @RequestMapping("/1")
    public void tset() {
        String call = dashScopeChatModel.call("你是谁");
        System.out.println(call);
    }
}
