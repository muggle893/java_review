package org.txf.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.txf.api"})
public class OrderServiceSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceSpringBootApplication.class, args);
    }
}
