package org.txf.qianfan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.*;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@Slf4j

public class ImageController {
    @Autowired
    private OpenAiImageModel openAiImageModel;

    @RequestMapping("/gen")
    public void gen() {
        ImageOptions imageOptions = ImageOptionsBuilder
                .builder()
                .height(1024)
                .width(1024)
                .N(1)
                .responseFormat("url")
                .build();
        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt("用手绘风格生成一张中式美女图"));
        Image output = imageResponse.getResult().getOutput();
        log.info("图片url: " + output.getUrl().toString());
    }
}
