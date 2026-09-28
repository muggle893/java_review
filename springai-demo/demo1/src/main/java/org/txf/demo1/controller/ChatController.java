package org.txf.demo1.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatClient chatClient;

    @RequestMapping("/call")
    public String testCall(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    // jdk16新特性，用record定义一个类
    record Recipe(String dish, List<String> ingredients) {

    }

    @RequestMapping("/entity")
    public String testEntity(String message) {
        Recipe entity = chatClient.prompt()
                .user(String.format("请帮我生成%s的食谱,要非常具体，每一个原料的克数都要标注出来", message))
                .call()
                .entity(Recipe.class);
        return entity.toString();
    }

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> testFlux(String message) {
        return chatClient.prompt()
                .user(message)
                .advisors(new SimpleLoggerAdvisor())
                .stream()
                .content();
    }

    @RequestMapping("/sse")
    public void testSSE(HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/event-stream;charset=utf-8");
        PrintWriter out = response.getWriter();
        String s = "";
        for (int i = 0; i < 20; i++) {
            s = "event: " + "foo\n";
            s += "data: " + "当前时间：" + new Date().toString() + "\n\n";
            out.write(s);
            out.flush();
            Thread.sleep(1000);
        }
    }
}
