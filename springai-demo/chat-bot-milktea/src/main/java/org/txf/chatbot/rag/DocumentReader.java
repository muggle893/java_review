package org.txf.chatbot.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class DocumentReader {
    @Autowired
    ResourceLoader resourceLoader;


    public Resource[] getResources(String path) throws IOException {
        ResourcePatternResolver resolver = new
                PathMatchingResourcePatternResolver(resourceLoader);
        return resolver.getResources(path);
    }

    public List<Document> loadMarkdowns(String path) throws IOException {
        log.info("开始加载markdown文件为Document对象.");
        // 先获取知识库文件资源
        List<Document> res = new ArrayList<>();
        try {
            // 获取Resource对象
            Resource[] resources = getResources(path);

            // 遍历Resources数组，获取文档对象
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                // 使用文档加载器
                MarkdownDocumentReaderConfig readerConfig = MarkdownDocumentReaderConfig.builder()
                        .withIncludeBlockquote(false)
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withAdditionalMetadata("filename", filename)
                        .build();
                MarkdownDocumentReader markdownDocumentReader = new MarkdownDocumentReader(resource, readerConfig);
                // 加载
                List<Document> documents = markdownDocumentReader.get();
                // 放到最终的结果集合中
                res.addAll(documents);
            }
        } catch (IOException e) {
            log.error("加载文档对象失败.");
            throw e;
        }
        log.info("加载markdown文件为Document对象完成.");
        return res;
    }

}
