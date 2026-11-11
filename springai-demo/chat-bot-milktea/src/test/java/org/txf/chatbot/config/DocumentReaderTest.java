package org.txf.chatbot.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.txf.chatbot.rag.DocumentReader;

import java.io.IOException;
import java.util.List;


@Slf4j
@SpringBootTest
class DocumentReaderTest {
    @Autowired
    DocumentReader documentReader;
    @Test
    void loadMarkdown() {
        String path = "classpath:knowledge-base/*.md";
        try {
            List<Document> documents = documentReader.loadMarkdowns(path);
            documents.forEach(System.out::println);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


}