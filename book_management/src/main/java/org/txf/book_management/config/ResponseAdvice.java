package org.txf.book_management.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.txf.book_management.result.RequestResult;

@ControllerAdvice
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // string类型要特殊处理
        if (body instanceof String) {
            String res;
            try {
                return objectMapper.writeValueAsString(RequestResult.success(body));
            } catch (JsonProcessingException e) {
                log.error("Json processing error", e);
            }
        }

        // 如果已经是ResultType
        if (body instanceof RequestResult) {
            return body;
        }
        return RequestResult.success(body);
    }
}
