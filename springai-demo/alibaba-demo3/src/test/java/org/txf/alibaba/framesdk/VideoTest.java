package org.txf.alibaba.framesdk;

import com.alibaba.cloud.ai.dashscope.api.DashScopeVideoApi;
import com.alibaba.cloud.ai.dashscope.spec.DashScopeModel;
import com.alibaba.cloud.ai.dashscope.video.DashScopeVideoModel;
import com.alibaba.cloud.ai.dashscope.video.DashScopeVideoOptions;
import com.alibaba.cloud.ai.dashscope.video.VideoPrompt;
import com.alibaba.cloud.ai.dashscope.video.VideoResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;
import org.txf.alibaba.util.FileUtil;
import org.txf.alibaba.util.VideoUtil;

@SpringBootTest
public class VideoTest {
    private static final Logger logger = LoggerFactory.getLogger(VideoTest.class);

    private static final String API_KEY_ENV = "ali-api-key";
    private final String SAVE_PATH = System.getProperty("user.dir") + "/video/";

    private final DashScopeVideoModel videoModel;

    public VideoTest() {
        DashScopeVideoApi videoApi = DashScopeVideoApi.builder().apiKey(System.getenv(API_KEY_ENV)).build();
        RetryTemplate retryTemplate = RetryTemplate.builder().maxAttempts(30) // Increase max attempts for video generation
                .fixedBackoff(10000) // 10 seconds between retries
                .build();

        this.videoModel = DashScopeVideoModel.builder()
                .videoApi(videoApi)
                .retryTemplate(retryTemplate)
                .build();

    }

    /**
     * 通义万相-图生视频-基于首帧
     */
    @Test
    public void first() {
        String prompt = "一幅都市奇幻艺术的场景。一个充满动感的涂鸦艺术角色。一个由喷漆所画成的少年，正从一面混凝土墙上活过来。他一边用极快的语速演唱一首英文rap，一边摆着一个经典的、充满活力的说唱歌手姿势。场景设定在夜晚一个充满都市感的铁路桥下。灯光来自一盏孤零零的街灯，营造出电影般的氛围，充满高能量和惊人的细节。视频的音频部分完全由他的rap构成，没有其他对话或杂音。";
        String imgUrl = "https://help-static-aliyun-doc.aliyuncs.com/file-manage-files/zh-CN/20250925/wpimhv/rap.png";
        String audioUrl = "https://help-static-aliyun-doc.aliyuncs.com/file-manage-files/zh-CN/20250925/ozwpvi/rap.mp3";
        // Build options matching curl command
        DashScopeVideoOptions options = DashScopeVideoOptions.builder()
                .model(DashScopeModel.VideoModel.WAN26_I2V_FLASH.getName())
                .input(DashScopeVideoOptions.InputOptions.builder()
                        .prompt(prompt)
                        .imgUrl(imgUrl)
                        .audioUrl(audioUrl)
                        .build()
                )
                .parameters(DashScopeVideoOptions.ParametersOptions.builder()
                        .resolution("720P")
                        .promptExtend(true)
                        .duration(10)
                        .shotType("multi")
                        .build())
                .build();

        VideoPrompt videoPrompt = VideoPrompt.builder()
                .options(options)
                .build();

        VideoResponse videoResponse = videoModel.call(videoPrompt);
        String videoUrl = videoResponse.getResult().getOutput().videoUrl();
        logger.info("视频生成成功，URL: {}", videoUrl);

        // 生成文件名
        String fileExtension = VideoUtil.getVideoExtension(videoUrl);
        String fileName = "基于首帧——多镜头叙事" + fileExtension;

        // 保存到当前模块的 resources 目录
        String filePath = SAVE_PATH + fileName;
        boolean success = FileUtil.url2File(videoUrl, filePath);

        if (success) {
            logger.info("视频保存成功: {}", filePath);
        } else {
            logger.error("视频保存失败: {}", filePath);
        }
    }
    /**
     * 通义万相-图生视频-基于首尾帧
     */
    @Test
    public void firstLast() {
        String prompt = "写实风格，一只黑色小猫好奇地看向天空，镜头从平视逐渐上升，最后俯拍它的好奇的眼神。";
        String firstFrameUrl = "https://wanx.alicdn.com/material/20250318/first_frame.png";
        String lastFrameUrl = "https://wanx.alicdn.com/material/20250318/last_frame.png";
        // Build options matching curl command
        DashScopeVideoOptions options = DashScopeVideoOptions.builder()
                .model(DashScopeModel.VideoModel.WAN22_KF2V_FLASH.getName())
                .input(DashScopeVideoOptions.InputOptions.builder()
                        .prompt(prompt)
                        .firstFrameUrl(firstFrameUrl)
                        .lastFrameUrl(lastFrameUrl)
                        .build()
                )
                .parameters(DashScopeVideoOptions.ParametersOptions.builder()
                        .resolution("480P")
                        .promptExtend(true)
                        .build())
                .build();

        VideoPrompt videoPrompt = VideoPrompt.builder()
                .options(options)
                .build();

        VideoResponse videoResponse = videoModel.call(videoPrompt);
        String videoUrl = videoResponse.getResult().getOutput().videoUrl();
        logger.info("视频生成成功，URL: {}", videoUrl);

        // 生成文件名
        String fileExtension = VideoUtil.getVideoExtension(videoUrl);
        String fileName = "基于首尾帧——首位帧生视频" + fileExtension;

        // 保存到当前模块的 resources 目录
        String filePath = SAVE_PATH + fileName;
        boolean success = FileUtil.url2File(videoUrl, filePath);

        if (success) {
            logger.info("视频保存成功: {}", filePath);
        } else {
            logger.error("视频保存失败: {}", filePath);
        }
    }

    /**
     * 通义万相-文生视频（多镜头叙事）
     */
    @Test
    public void t2vMultiShot() {
        String prompt = "一幅史诗级可爱的场景。一只小巧可爱的卡通小猫将军，身穿细节精致的金色盔甲，头戴一个稍大的头盔，勇敢地站在悬崖上。他骑着一匹虽小但英勇的战马，说：\\\"青海长云暗雪山，孤城遥望玉门关。黄沙百战穿金甲，不破楼兰终不还。\\\"悬崖下方，一支由老鼠组成的、数量庞大、无穷无尽的军队正带着临时制作的武器向前冲锋。这是一个戏剧性的、大规模的战斗场景，灵感来自中国古代的战争史诗。远处的雪山上空，天空乌云密布。整体氛围是\\\"可爱\\\"与\\\"霸气\\\"的搞笑和史诗般的融合。";
        String audioUrl = "https://help-static-aliyun-doc.aliyuncs.com/file-manage-files/zh-CN/20250923/hbiayh/%E4%BB%8E%E5%86%9B%E8%A1%8C.mp3";

        DashScopeVideoOptions options = DashScopeVideoOptions.builder()
                .model(DashScopeModel.VideoModel.WAN26_T2V.getName())
                .input(DashScopeVideoOptions.InputOptions.builder()
                        .prompt(prompt)
                        .audioUrl(audioUrl)
                        .build()
                )
                .parameters(DashScopeVideoOptions.ParametersOptions.builder()
                        .size("1280*720")
                        .promptExtend(true)
                        .duration(10)
                        .shotType("multi")
                        .build())
                .build();

        VideoPrompt videoPrompt = VideoPrompt.builder()
                .options(options)
                .build();

        VideoResponse videoResponse = videoModel.call(videoPrompt);
        String responseVideoUrl = videoResponse.getResult().getOutput().videoUrl();
        logger.info("视频生成成功，URL: {}", responseVideoUrl);

        String fileExtension = VideoUtil.getVideoExtension(responseVideoUrl);
        String fileName = "通义万相-文生视频多镜头叙事" + fileExtension;
        String filePath = SAVE_PATH + fileName;
        boolean success = FileUtil.url2File(responseVideoUrl, filePath);

        if (success) {
            logger.info("视频保存成功: {}", filePath);
        } else {
            logger.error("视频保存失败: {}", filePath);
        }
    }
}
