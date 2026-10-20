package org.txf.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
public class FeignTestSpringBootStartUp {
    public static void main(String[] args) {
        SpringApplication.run(FeignTestSpringBootStartUp.class, args);
    }
}
