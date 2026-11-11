package org.txf.chatbot.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.txf.chatbot.rag.DocumentReader;
import org.txf.chatbot.rag.TokenTextSplitter;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
class TokenTextSplitterTest {
    @Autowired
    TokenTextSplitter tokenTextSplitter;
    @Autowired
    DocumentReader documentReader;

    @Test
    void doSplit() {
        String path = "classpath:knowledge-base/*.md";
        try {
            List<Document> documents = documentReader.loadMarkdowns(path);
            log.info("分割前的长度：{}", documents.size());
            List<Document> splits = tokenTextSplitter.split(documents);
            log.info("分割后的长度：{}", splits.size());
            splits.forEach(System.out::println);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}