package org.txf.alibaba.sdk;

import com.alibaba.dashscope.audio.asr.recognition.Recognition;
import com.alibaba.dashscope.audio.asr.recognition.RecognitionParam;
import com.alibaba.dashscope.audio.asr.transcription.Transcription;
import com.alibaba.dashscope.audio.asr.transcription.TranscriptionParam;
import com.alibaba.dashscope.audio.asr.transcription.TranscriptionQueryParam;
import com.alibaba.dashscope.audio.asr.transcription.TranscriptionResult;
import com.alibaba.dashscope.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;

public class Audio2TextTest {
    String apiKey = System.getenv("ali-api-key");
    @Test
    public void test1() {
        Assert.notNull(apiKey, "没有读取到环境变量的apikey");
        // 以下为华北2（北京）地域的配置，调用时请将"{WorkspaceId}"替换为真实的业务空间ID，各地域的配置不同。
        Constants.baseHttpApiUrl = "https://ws-9vwgk7xu4keolyxv.cn-beijing.maas.aliyuncs.com/api/v1";
        // 创建转写请求参数
        TranscriptionParam param =
                TranscriptionParam.builder()
                        // 若没有将API Key配置到环境变量中，需将apiKey替换为自己的API Key
                        .apiKey(apiKey)
                        .model("paraformer-v2")
                        // “language_hints”只支持paraformer-v2模型
                        .parameter("language_hints", new String[]{"zh", "en"})
                        .fileUrls(
                                Arrays.asList("https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/paraformer/hello_world_female2.wav",
                                        "https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/paraformer/hello_world_male2.wav"))
                        .build();
        try {
            Transcription transcription = new Transcription();
            // 提交转写请求
            TranscriptionResult result = transcription.asyncCall(param);
            System.out.println("RequestId: " + result.getRequestId());
            // 阻塞等待任务完成并获取结果
            result = transcription.wait(
                    TranscriptionQueryParam.FromTranscriptionParam(param, result.getTaskId()));
            // 打印结果
            System.out.println(result.getOutput());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        System.exit(0);
    }

    // 实时语音识别
    @Test
    public void test2() {
        // 以下为华北2（北京）地域的配置，调用时请将"{WorkspaceId}"替换为真实的业务空间ID，各地域的配置不同。
        Constants.baseWebsocketApiUrl = "wss://ws-9vwgk7xu4keolyxv.cn-beijing.maas.aliyuncs.com/api-ws/v1/inference";
        // 创建Recognition实例
        Recognition recognizer = new Recognition();
        // 创建RecognitionParam
        RecognitionParam param =
                RecognitionParam.builder()
                        // 若没有将API Key配置到环境变量中，需将下面这行代码注释放开，并将apiKey替换为自己的API Key
                        .apiKey(apiKey)
                        .model("paraformer-realtime-v2")
                        .format("mp3")
                        .sampleRate(22050)
                        // “language_hints”只支持paraformer-realtime-v2模型
                        .parameter("language_hints", new String[]{"zh", "en"})
                        .build();

        try {
            // 解析语音识别的结果
            File file = new File("output.mp3");
            String res = recognizer.call(param, file);
            // 使用Gson类来解析json字符串
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
            JsonArray sentences = jsonObject.getAsJsonArray("sentences");
            sentences.forEach(jsonElement -> {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                String text = asJsonObject.get("text").getAsString();
                System.out.println(text);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 任务结束后关闭 WebSocket 连接
            recognizer.getDuplexApi().close(1000, "bye");
        }
        System.out.println(
                "[Metric] requestId: "
                        + recognizer.getLastRequestId()
                        + ", first package delay ms: "
                        + recognizer.getFirstPackageDelay()
                        + ", last package delay ms: "
                        + recognizer.getLastPackageDelay());
        System.exit(0);
    }



}
