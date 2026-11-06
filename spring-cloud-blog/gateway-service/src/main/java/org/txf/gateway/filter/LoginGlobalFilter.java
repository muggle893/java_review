package org.txf.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.txf.common.pojo.Result;
import org.txf.common.utils.JWTUtils;
import org.txf.gateway.config.AuthWhiteNameProperties;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class LoginGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private ObjectMapper objectMapper;

    // 白名单
    private List<String> passTable;

    @Autowired
    private AuthWhiteNameProperties authWhiteName;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求中获取请求的路径判断是否在白名单中
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        // 在白名单中就放行
        if (authWhiteName.getUrls().contains(path)) {
            return chain.filter(exchange);
        }

        //从header中获取token
        String jwtToken = request.getHeaders().getFirst("user_token");
        log.info("从header中获取token:{}",jwtToken);

        //验证用户token
        Claims claims = JWTUtils.parseJWT(jwtToken);
        if (claims!=null){
            log.info("令牌验证通过, 放行");
            return chain.filter(exchange);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Result result = Result.fail("令牌验证失败");
        DataBuffer dataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
        return response.writeWith(Mono.just(dataBuffer));
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
