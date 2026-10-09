package org.txf.demo1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

@RestController
@RequestMapping("/flux")
public class FluxApiController {
    // 每间隔一秒输出系统的时间
    @RequestMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        // Flux.interval(...) 用来创建一个按固定时间间隔产生递增数字的数据流。
        return (Flux<String>) Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> new Date().toString());
    }
}
