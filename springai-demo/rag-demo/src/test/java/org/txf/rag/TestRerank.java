package org.txf.rag;

import com.alibaba.cloud.ai.advisor.RetrievalRerankAdvisor;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.rerank.DashScopeRerankModel;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRerank {
    @Autowired
    DashScopeChatModel dashScopeChatModel;
    @Autowired
    DashScopeRerankModel dashScopeRerankModel;

    // 这里使用的向量数据库是pinecone的向量数据库
    @Autowired
    VectorStore vectorStore;

    @Test
    public void testRerank() {
        // 创建RetrievalRerankAdvisor对象进行重排序
        RetrievalRerankAdvisor retrievalRerankAdvisor = new RetrievalRerankAdvisor(vectorStore,
                dashScopeRerankModel,
                SearchRequest
                    .builder()
                    .topK(200)
                    .build());

        ChatClient chatClient = ChatClient.builder(dashScopeChatModel)
                .defaultAdvisors(retrievalRerankAdvisor)
                .build();

        String content = chatClient.prompt()
                .user("会员打几折")
                .call()
                .content();

        System.out.println("结果：" + content);
    }
}
