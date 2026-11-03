package org.txf.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.txf.blog.api.BlogServiceApi;

@EnableFeignClients(clients = {BlogServiceApi.class})
@SpringBootApplication
public class UserServiceSpringBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceSpringBlogApplication.class, args);
	}

}
