package org.txf.alibabademo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/ml")
public class MultiModelController {
    ChatClient chatClient;
    public MultiModelController(ChatClient.Builder builder) {
        this.chatClient = builder.defaultAdvisors(new SimpleLoggerAdvisor()).defaultSystem("在每次回答的时候，前面都都带上{word}").build();
    }

    @RequestMapping("/image")
    public String chat() throws URISyntaxException, MalformedURLException {
        String prompt = "介绍一下图片中有什么.";
        String url = "https://dashscope.oss-cn-beijing.aliyuncs.com/images/dog_and_girl.jpeg";
        List<Media> mediaList = List.of(new Media(MimeTypeUtils.IMAGE_JPEG, new
                URI(url).toURL().toURI()));
        UserMessage message =
                UserMessage.builder().text(prompt).media(mediaList).build();
        return chatClient.prompt(new Prompt(message)).call().content();
    }
}
