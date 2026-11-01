package org.txf.rag;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class VectorStoreTest {

//    @TestConfiguration
//    static class TestConfig {
//        @Bean
//        public VectorStore vectorStore(DashScopeEmbeddingModel embeddingModel){
//            return SimpleVectorStore.builder(embeddingModel).build();
//        }
//    }

    @Autowired
    VectorStore vectorStore;
    /**
     * 测试SimpleVectorStore
     */
    @Test
    public void testVectorStore(@Value("classpath:/file/rule.txt") Resource resource) {
        // 1.从文件中提取文档对象
        TextReader reader = new TextReader(resource);
        List<Document> readDocs = reader.get();

        // 2.使用TextSplitter进行文档拆分
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(200)
                .withKeepSeparator(true)
                .withMaxNumChunks(1100)
                .withMinChunkLengthToEmbed(10)
                .build();
        List<Document> res = splitter.apply(readDocs);

        // 3.使用vectorstore存到向量数据库中
        vectorStore.add(res);

        List<Document> documents = vectorStore.similaritySearch("机器学习");
        documents.stream().forEach(System.out::println);
    }

    /**
     * 测试Redis Vector Store
     */

    @Test
    void testRedisVectorStore(@Value("classpath:file/rule.txt") Resource resource){
        List<Document> documents = List.of(
                new Document("Spring AI 很强⼤！⽀持多种向量数据库",
                        Map.of("category", "tech", "year", 2024)),
                new Document("北京是中国的⾸都, 历史悠久", Map.of("category", "geo",
                        "year", 2023)),
                new Document("⼈⼯智能正在改变世界", Map.of("category", "ai", "year",
                        2024)));
        vectorStore.add(documents);
    }

    @Test
    void testPineconeVectorStore(@Value("classpath:file/rule.txt") Resource resource){
        // 1.从文件中提取文档对象
        TextReader reader = new TextReader(resource);
        List<Document> readDocs = reader.get();

        // 2.使用TextSplitter进行文档拆分
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(200)
                .withKeepSeparator(true)
                .withMaxNumChunks(1100)
                .withMinChunkLengthToEmbed(10)
                .build();
        List<Document> res = splitter.apply(readDocs);

        // 3.使用vectorstore存到向量数据库中
        vectorStore.add(res);

        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                        .query("订单")
                        .topK(2)
                        .similarityThreshold(0.4)
                .build());
        documents.stream().forEach(System.out::println);
    }

    @Test
    void testPineconeVectorStoreSearch(@Value("classpath:file/rule.txt") Resource resource){
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                .query("会员卡")
                .topK(2)
                .similarityThreshold(0.4)
                .build());
        documents.stream().forEach(System.out::println);
    }
}
