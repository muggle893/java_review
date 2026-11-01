package org.txf.rag;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.writer.FileDocumentWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootTest
public class WriterTest {

    @Test
    void testFileWriter(@Value("classpath:file/rule.txt") Resource resource){
        TextReader reader = new TextReader(resource);
        List<Document> documents = reader.get();
        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> apply = splitter.apply(documents);
        FileDocumentWriter writer = new FileDocumentWriter("output.txt",
                true, MetadataMode.ALL, false);
        writer.accept(apply);
    }
}
