package org.txf.qianfan.controller;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/speech")
public class SpeechController {
    @Autowired
    OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @RequestMapping("/gen")
    public void speech() throws IOException {
        String prompt = "⼩池, 泉眼⽆声惜细流, 树阴照⽔爱晴柔. ⼩荷才露尖尖⻆, 早有蜻蜓⽴上头";
        SpeechPrompt speechPrompt = new SpeechPrompt(prompt);
        SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);

        File file = new File(System.getProperty("user.dir") + "/output.mp3");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(response.getResult().getOutput());
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
