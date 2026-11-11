package org.txf.chatbot.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.txf.chatbot.rag.DocumentReader;
import org.txf.chatbot.rag.KeyWordEnricher;
import org.txf.chatbot.rag.TokenTextSplitter;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
public class DataInit {
    @Value("${data.need-load}")
    private boolean needLoad = true;

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    @Autowired
    DocumentReader documentReader;
    @Autowired
    TokenTextSplitter tokenTextSplitter;
    @Autowired
    KeyWordEnricher keyWordEnricher;
    @Autowired
    VectorStore vectorStore;

    @PostConstruct
    public void init() {
        if (!needLoad) {
            log.info("知识库已经初始化完成无序再初始化.");
            return;
        }
        // 加载文档
        String filePath = "classpath:knowledge-base/*.md";
        log.info("知识库初始化...");
        try {
            log.info("加载文档...");
            List<Document> documents = documentReader.loadMarkdowns(filePath);

            log.info("分割文档...");
            List<Document> splits = tokenTextSplitter.split(documents);

            // 分批处理文档，添加关键词并且向量化
            processEnrichAndEmbed(splits, 10);
            log.info("知识库初始化完成.");
        } catch (Exception e) {
            log.error("加载知识库失败!", e);
        }
    }

    /**
     * 多线程处理关键词提取和向量嵌入
     * @param documents 文档对象列表
     */
    public void processEnrichAndEmbed(List<Document> documents, int batchSize) {
        log.info("分批处理文档对象...");
        // 10个Document为一批，让一个线程处理一批
        // 先把Document分批操作
        // 先计算分为多少批次,比如197, 197 / 10 = 19.7 ,向上取整就是20
        int n = documents.size();
        int t = (int)Math.ceil((double)n / batchSize);
        List<List<Document>> lists = IntStream.range(0, t)
                .mapToObj(i -> {
                    int start = i * batchSize;
                    int end = Math.min(n, start + batchSize);
                    return documents.subList(start, end);
                })
                .collect(Collectors.toList());

        CountDownLatch countDownLatch = new CountDownLatch(t);
        log.info("总批次数：{}", t);
        for (List<Document> list : lists) {
            threadPool.submit(()->{
                try {
                    // 关键词提取
                    List<Document> enrich = keyWordEnricher.enrich(list);
                    // 向量化
                    vectorStore.add(enrich);

                    log.info("批次处理成功, 文档数量:{}", enrich.size());
                } catch (Exception e) {
                    log.warn("批次关键词提取和向量化失败.");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        // 等待任务完成
        try {
            if (!countDownLatch.await(15, TimeUnit.MINUTES)) {
                log.error("批量导⼊超时, 仍有任务未完成");
            } else {
                log.info("Documents批量任务执行完成");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
