package org.txf.alibaba.framesdk;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class ChatImageModelTest {
    @Autowired
    private DashScopeChatModel dashScopeChatModel;
    @Autowired
    private DashScopeImageModel dashScopeImageModel;
    @Test
    public void test() {
        String content = dashScopeChatModel.call("你是什么模型?");
        log.info(content);
    }

    @Test
    public void text2Image() {

        String prompt = "中国⼥孩, 圆脸, 看着镜头, 优雅的⺠族服装, 商业摄影, 室外, 电影级光 照, 半⾝特写, 精致的淡妆, 锐利的边缘. ";
        DashScopeImageOptions imageOptions = DashScopeImageOptions.builder()
                .withModel("wan2.2-t2i-flash")
                .build();
        ImageResponse imageResponse = dashScopeImageModel.call(new ImagePrompt(prompt, imageOptions));
        String  imageUrl = imageResponse.getResult().getOutput().getUrl();
        System.out.println(imageUrl);
    }

    @Test
    public void text2Img3() {
        String prompt = "孩⼦在海边玩耍";
        DashScopeImageOptions imageOptions = DashScopeImageOptions.builder()
                .withModel("wan2.2-t2i-flash")
                .withN(3)
                .build();
        ImageResponse imageResponse = dashScopeImageModel.call(new
                ImagePrompt(prompt,imageOptions));
        List<ImageGeneration> results = imageResponse.getResults();
        List<String> urls = new ArrayList<>();
        for(ImageGeneration imageGeneration: results){
            urls.add(imageGeneration.getOutput().getUrl());
        }
        System.out.println(urls);
    }

    @Test
    public void text2Img4() {
        String prompt = "生成一张新疆草原风景图";
        DashScopeImageOptions imageOptions = DashScopeImageOptions.builder()
                .withModel("wan2.2-t2i-flash")
                .withN(1)
                .withWatermark(true)
                .build();
        ImageResponse imageResponse = dashScopeImageModel.call(new
                ImagePrompt(prompt,imageOptions));
        List<ImageGeneration> results = imageResponse.getResults();
        List<String> urls = new ArrayList<>();
        for(ImageGeneration imageGeneration: results){
            urls.add(imageGeneration.getOutput().getUrl());
        }
        System.out.println(urls);
    }

    @Test
    public void text2Img5() {
        String prompt = "生成一张超级好看的新疆草原风景图";
        DashScopeImageOptions imageOptions = DashScopeImageOptions.builder()
                .withModel("wan2.2-t2i-flash")
                .withN(1)
                .withWatermark(true)
                .withWidth(512)
                .withHeight(512)
                .build();
        ImageResponse imageResponse = dashScopeImageModel.call(new
                ImagePrompt(prompt,imageOptions));
        List<ImageGeneration> results = imageResponse.getResults();
        List<String> urls = new ArrayList<>();
        for(ImageGeneration imageGeneration: results){
            urls.add(imageGeneration.getOutput().getUrl());
        }
        System.out.println(urls);
    }
}
