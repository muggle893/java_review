package org.txf.rag;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.jsoup.JsoupDocumentReader;
import org.springframework.ai.reader.jsoup.config.JsoupDocumentReaderConfig;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootTest
public class DocumentReaderTest {

    /**
     * 测试纯文本文件的读取
     */
    @Test
    public void testTxtReader(@Value("classpath:/file/text.txt") Resource resource) {
        // TextReader也可以传一个url参数来构造TextReader
        TextReader textReader = new TextReader(resource);
        textReader.getCustomMetadata().put("username", "zhangsan");
        List<Document> documents = textReader.get();
        System.out.println("读取到的文本文件的document个数:" + documents.size());
        for (Document document : documents) {
            System.out.println(document);
        }

        // 使用TokenTextSplitter把文本拆分成更小的块
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<Document> chunks = tokenTextSplitter.apply(documents);
        for (Document document : chunks) {
            System.out.println(document);
        }
    }

    /**
     * 测试json文件的读取
     * @param jsonResource
     * @param jsonResource2
     */
    @Test
    public void testJsonReader(@Value("classpath:/file/web.json") Resource jsonResource, @Value("classpath:/file/webArray.json") Resource jsonResource2) {
        JsonReader reader = new JsonReader(jsonResource);
        // 如果是一个对象的话只会读取到一个Document
        List<Document> documents = reader.get();

        documents.stream().forEach((doc) ->{
            System.out.println(doc.toString());
        });
        JsonReader reader2 = new JsonReader(jsonResource2);

        List<Document> documents2 = reader2.get();

        documents2.stream().forEach((doc) ->{
            System.out.println(doc.toString());
        });
    }


    @Test
    public void testHtmlReader(@Value("classpath:/file/my-page.html") Resource resource) {
        JsoupDocumentReaderConfig config = JsoupDocumentReaderConfig.builder()
                .selector("article p")
                .groupByElement(true)
                .build();
        JsoupDocumentReader reader = new JsoupDocumentReader(resource, config);
        List<Document> documents = reader.get();
        System.out.printf("documents: %d\n", documents.size());
        for (Document document : documents) {
            System.out.println(document.toString());
        }
    }

    @Test
    void testReadMarkdown(@Value("classpath:/file/code.md") Resource resource){
        MarkdownDocumentReaderConfig config =
                MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .withAdditionalMetadata("filename", "code.md")
                        .build();
        MarkdownDocumentReader reader = new MarkdownDocumentReader(resource,
                config);
        List<Document> documents = reader.get();
        System.out.printf("documents: %d\n", documents.size());
        for (Document document : documents) {
            System.out.println(document.getText());
            System.out.println(document.getMetadata());
            System.out.println("------------------");
        }
    }
}
