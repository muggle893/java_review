package org.txf.alibabademo.controller;

import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/ali")
public class AliConctroller {
    private final DashScopeImageModel dashScopeImageModel;
    ChatClient chatClient;

    public AliConctroller(ChatClient.Builder builder, DashScopeImageModel dashScopeImageModel) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).defaultSystem("在每次回答的时候，前面都都带上{word}").build();
        this.dashScopeImageModel = dashScopeImageModel;
    }

    @RequestMapping("/chat")
    public String chat(String msg) {
        return chatClient.prompt().user(msg).call().content();
    }

    @RequestMapping(value = "/stream", produces = "text/html;charset=utf-8")
    public Flux<String> chat2(String msg) {
        return chatClient.prompt().user(msg).stream().content();
    }

    record FilmActor(String actorName, List<String> films){}


    // 结构化输出
    @RequestMapping("/structdata")
    public String getFilmActor(){
        return chatClient.prompt().user("随机给我一名知名演员的电影列表").call().entity(FilmActor.class).toString();
    }

    @RequestMapping("/testword")
    public String setSystemWord(String msg) {
        String words = "我是小爱同学：";
        return chatClient
                .prompt()
                .user(msg)
                .system(sp -> sp.param("word", words))
                .call()
                .content();
    }


}
