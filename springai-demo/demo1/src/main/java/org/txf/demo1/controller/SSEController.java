package org.txf.demo1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@RestController
@RequestMapping("/sse")
@Slf4j
public class SSEController {
    private ChatClient chatClient;


    @RequestMapping("/retry")
    public void retry(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream; charset=utf-8");
        PrintWriter out = response.getWriter();
        String s = "retry: 2000\n";
        s += "data: " + new Date() + "\n\n";
        out.write(s);
        out.flush();
    }

    @RequestMapping("/event")
    public void event(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        log.info("测试sse协议自定义事件.");
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String s = "";
        for (int i = 0; i < 20; i++) {
            s = "event: foo\n";
            s += "data: " + new Date() + "\n\n";
            writer.write(s);
            writer.flush();
            Thread.sleep(1000);
        }
    }
}
