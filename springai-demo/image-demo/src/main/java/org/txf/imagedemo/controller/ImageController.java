package org.txf.imagedemo.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/image")
@Slf4j
public class ImageController {
    @Autowired
    private OpenAiImageModel openaiImageModel;

    @RequestMapping("/gen")
    public void generate(HttpServletResponse httpServletResponse) {
        log.info("调用生图模型.");
        ImagePrompt prompt = new ImagePrompt(
                "生成一张小狗睡在阳光海滩的图片",
                OpenAiImageOptions.builder()
                        .quality("medium")
                        .N(1)
                        .height(1024)
                        .width(1024)
                        .responseFormat("url")
                        .build()
        );

        ImageResponse response = openaiImageModel.call(prompt);

        Image image = response.getResult().getOutput();

        String b64 = image.getB64Json();
        httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decode = decoder.decode(b64);
            httpServletResponse.getOutputStream().write(decode);
            httpServletResponse.getOutputStream().flush();
        } catch(Exception e) {
            log.error("base64解码失败.");
        }
    }
}
