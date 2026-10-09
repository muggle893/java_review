package org.txf.demo1;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class TestFlux {
    @Test
    public void testCreateFlux() {
        // 1.使用just方法创建Flux
        Flux<Integer> flux1 = Flux.just(1, 2, 3);

        // 2.使用List或者Set创建
        Flux<String> flux2 = Flux.fromIterable(Arrays.asList("a", "b", "c"));

        // 3.用range()方法创建
        Flux<Integer> flux3 = Flux.range(1, 10);

        flux1.subscribe(System.out::println);
        flux2.subscribe(System.out::println);
        flux3.subscribe(System.out::println);
    }

    @Test
    public void testHandleData() throws InterruptedException {
        Flux<Integer> flux = Flux.range(1, 10);

        // 1.使用map方法来处理
        System.out.println("使用map处理数据.");
        flux.map(x -> x + 1).subscribe(System.out::println);

        // 2.file方法用来过滤数据，去掉那些不要的数据
        System.out.println("使用filter处理数据.");
        // 这里把偶数筛选出来
        flux.filter(x -> x % 2 == 0).subscribe(System.out::println);

        // 3.take方法可以用来限制元素个数
        System.out.println("限制元素个数");
        flux.take(5).subscribe(System.out::println);

        // 4.merge方法可以合并多个Flux（不保证顺序)
        System.out.println("merge合并多个Flux");
        Flux.merge(Flux.just("hello"), Flux.just("world")).subscribe(System.out::println);

        // 5.concat可以顺序拼接多个Flux
        System.out.println("concat拼接多个Flux");
        Flux.merge(Flux.just(1, 2, 3), Flux.just(4, 5, 6)).subscribe(System.out::println);


        // 6.delayElements(Duration.ofSecond())，可以用延迟推送数据
        System.out.println("delayElements延迟推送数据");
        flux.delayElements(Duration.ofSeconds(1)).subscribe(System.out::println);


        // main线程不能一下子关闭否则看不到延迟推送输出的数据
        Thread.sleep(10000);
    }

}
