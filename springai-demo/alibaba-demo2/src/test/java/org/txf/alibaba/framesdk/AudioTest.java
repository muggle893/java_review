package org.txf.alibaba.framesdk;


import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@SpringBootTest
public class AudioTest {

    @Autowired
    private DashScopeSpeechSynthesisModel speechSynthesisModel;

    @Test
    public void text2Audio() throws IOException {
        DashScopeSpeechSynthesisOptions options =
                DashScopeSpeechSynthesisOptions.builder()
                        .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt("⼩池, 泉眼⽆声惜细流, 树阴照⽔爱晴柔. ⼩荷才 露尖尖⻆, 早有蜻蜓⽴上头",options)
                );
        File file = new File( System.getProperty("user.dir") + "/output.mp3");
        System.out.println(file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Test
    public void text2Audio2() throws IOException {
        DashScopeSpeechSynthesisOptions options =
                DashScopeSpeechSynthesisOptions.builder()
                        .model("cosyvoice-v2")
                        .voice("longsanshu")
                        .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt("⼭村咏怀, ⼀去⼆三⾥, 烟村四五家. 亭台六七座, ⼋ 九⼗枝花",options)
        );
        File file = new File( System.getProperty("user.dir") + "/output.mp3");
        System.out.println(file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Test
    public void text2Audio3() throws IOException {
        DashScopeSpeechSynthesisOptions options =
                DashScopeSpeechSynthesisOptions.builder()
                        .model("cosyvoice-v2")
                        .voice("longmiao_v2")
                        .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt("⼭村咏怀, ⼀去⼆三⾥, 烟村四五家. 亭台六七座, ⼋ 九⼗枝花",options)
        );
        File file = new File( System.getProperty("user.dir") + "/output.mp3");
        System.out.println(file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


    @Test
    public void text2Audio4() throws IOException {
        DashScopeSpeechSynthesisOptions options =
                DashScopeSpeechSynthesisOptions.builder()
                        .model("cosyvoice-v2")
                        .voice("longmiao_v2")
                        .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt("⼭村咏怀, ⼀去⼆三⾥, 烟村四五家. 亭台六七座, ⼋ 九⼗枝花",options)
        );
        File file = new File( System.getProperty("user.dir") + "/output.mp3");
        System.out.println(file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


    @Test
    public void text2Audio5() throws IOException {
        DashScopeSpeechSynthesisOptions options =
                DashScopeSpeechSynthesisOptions.builder()
                        .model("cosyvoice-v2")
                        .voice("longmiao_v2")
                        .speed(0.5f)
                        .pitch(1.2d)
                        .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt("⼭村咏怀, ⼀去⼆三⾥, 烟村四五家. 亭台六七座, ⼋九⼗枝花", options)
        );
        File file = new File(System.getProperty("user.dir") + "/output.mp3");
        System.out.println(file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


    @Test
    public void text2Audio6() throws IOException {
        DashScopeSpeechSynthesisOptions options =
                DashScopeSpeechSynthesisOptions.builder()
                        .model("cosyvoice-v2")
                        .voice("longmiao_v2")
                        .volume(10)
                        .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt("⼭村咏怀, ⼀去⼆三⾥, 烟村四五家. 亭台六七座, ⼋ 九⼗枝花",options));
        File file = new File(System.getProperty("user.dir") + "/output.mp3");
        System.out.println(file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
