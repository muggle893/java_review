package org.txf.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.tool.DateTimeTools;
import org.txf.tool.WeatherTools;

import java.lang.reflect.Method;

@RequestMapping("/chat")
@RestController
public class ChatController {
    OpenAiChatModel openAiChatModel;

    ChatClient chatClient;

    @Resource(name = "weatherTools")
    ToolCallback weatherTools;

    public ChatController(OpenAiChatModel openAiChatModel) {
        this.chatClient = ChatClient
                .builder(openAiChatModel)
                // 设置默认工具
                .defaultTools(new DateTimeTools())
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
        this.openAiChatModel = openAiChatModel;
    }

    @RequestMapping("/1")
    public String chat1(String msg) {
        return  chatClient
                .prompt()
                .user(msg)
                .call()
                .content();
    }

    @RequestMapping("/tool")
    public String callToll(String msg) {
        return chatClient
                .prompt()
                .user(msg)
                .tools(new DateTimeTools())
                .call()
                .content();
    }

    /**
     * 使用chatModel来添加工具
     * @param msg
     * @return
     */
    @RequestMapping("/tool2")
    public String callTool2(String msg) {
        ToolCallback[] tools = ToolCallbacks.from(new DateTimeTools());
        ChatOptions options = ToolCallingChatOptions
                .builder()
                .toolCallbacks(tools)
                .build();
        return openAiChatModel
                .call(new Prompt(msg, options))
                .getResult()
                .getOutput()
                .getText();
    }

    /**
     * 使用编程式声明工具
     * @param msg
     * @return
     */
    @RequestMapping("/tool3")
    public String callTool3(String msg) {
        // 使用反射得到Method对象
        // 使用MethodToolCallBack来创建CallBack对象
        Method method = ReflectionUtils.findMethod(WeatherTools.class, "getCurrentWeatherByCityName", String.class);
        MethodToolCallback weatherTools = MethodToolCallback
                .builder()
                .toolDefinition(ToolDefinitions
                        .builder(method)
                        .description("根据给定的城市名字，获取城市的天气.")
                        .build())
                .toolMethod(method)
                .toolObject(new WeatherTools())
                .build();
        return chatClient
                .prompt()
                .user(msg)
                .toolCallbacks(weatherTools)
                .call()
                .content();

    }

    /**
     * 使用编程式声明工具
     * 在ToolConfig中注入callback对象然后直接使用
     * @param msg
     * @return
     */
    @RequestMapping("/tool4")
    public String callTool4(String msg) {
        return chatClient
                .prompt()
                .user(msg)
                .toolCallbacks(weatherTools)
                .call()
                .content();

    }
}
