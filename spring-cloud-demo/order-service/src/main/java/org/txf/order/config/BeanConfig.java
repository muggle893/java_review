package org.txf.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    // 把RestTemplate这个对象注入到spring中
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
