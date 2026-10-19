package org.txf.qianfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QianFanApplication {
    public static void main(String[] args) {
        // 名称必须和你实际设置的环境变量一致
        String key = System.getenv("qianfan-api-key");
        boolean exists = key != null && !key.isBlank();

        System.out.println("环境变量是否读到：" + exists);

        var context = SpringApplication.run(QianFanApplication.class, args);

        String configuredKey = context.getEnvironment()
                .getProperty("spring.ai.openai.api-key");

        System.out.println("Spring配置是否与环境变量一致："
                + (exists && key.equals(configuredKey)));
    }
}
