package org.txf.demo1.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/sse")
@Slf4j
public class SSEController {
    @RequestMapping("/stream")
    public void stream(HttpServletResponse response) throws IOException, InterruptedException {
        // 设置response的数据格式
        response.setContentType("text/event-stream;charset=utf-8");
        response.setHeader("Connection", "alive");
        for (int i = 0; i < 10; i++) {
            String message = "data: 你好.当前时间为->" + new Date() + "\n\n";
            response.getWriter().write(message);
            response.getWriter().flush();
            Thread.sleep(1000);
        }
        // 在结束的时候定义done事件
        String donemessage = "data: \nevent: done\n\n";
        response.getWriter().write(donemessage);
        response.getWriter().flush();
    }
}
