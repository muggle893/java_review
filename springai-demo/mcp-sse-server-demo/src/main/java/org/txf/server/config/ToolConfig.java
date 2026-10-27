package org.txf.server.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.txf.server.service.UserInfoService;

@Configuration
public class ToolConfig {
    @Bean
    public ToolCallbackProvider getToolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(new UserInfoService())
                .build();
    }
}
