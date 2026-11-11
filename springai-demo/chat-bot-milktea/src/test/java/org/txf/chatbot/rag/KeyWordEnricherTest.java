package org.txf.chatbot.rag;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
class KeyWordEnricherTest {
    @Autowired
    TokenTextSplitter tokenTextSplitter;
    @Autowired
    DocumentReader documentReader;
    @Autowired
    KeyWordEnricher keyWordEnricher;

    @Test
    void enrich() {
        String path = "classpath:knowledge-base/*.md";
        try {
            List<Document> documents = documentReader.loadMarkdowns(path);
            log.info("分割前的文档个数：{}", documents.size());
            List<Document> splits = tokenTextSplitter.split(documents);
            log.info("分割后的文档个数：{}", splits.size());
            splits.forEach(System.out::println);
            System.out.println("----------------------------------------------关键词加强----------------------------------");
            List<Document> enrich = keyWordEnricher.enrich(splits);
            enrich.forEach(System.out::println);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}