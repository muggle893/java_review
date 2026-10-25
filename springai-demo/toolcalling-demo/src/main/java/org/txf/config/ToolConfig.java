package org.txf.config;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.txf.tool.WeatherTools;

import java.lang.reflect.Method;

@Configuration
public class ToolConfig {
    @Bean(name = "weatherTools")
    public ToolCallback weatherToolCallback(){
        Method method = ReflectionUtils.findMethod(WeatherTools.class,
                "getCurrentWeatherByCityName", String.class);
        ToolCallback weatherTools = MethodToolCallback.builder()
                .toolDefinition(ToolDefinitions.builder(method)
                        .description("根据给定的城市名称, 获取城市当前的天气 ")
                        .build())
                .toolMethod(method)
                .toolObject(new WeatherTools())
                .build();
        return weatherTools;
    }
}
