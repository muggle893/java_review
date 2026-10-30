package org.txf.rag;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RagTest {
    @Autowired
    DashScopeEmbeddingModel dashScopeEmbeddingModel;

    @Autowired
    DashScopeChatModel dashScopeChatModel;

    @Autowired
    VectorStore vectorStore;

    @TestConfiguration
    static class RagConfig {

        @Bean
        public VectorStore getVectorStore(DashScopeEmbeddingModel dashScopeEmbeddingModel) {
            return SimpleVectorStore.builder(dashScopeEmbeddingModel)
                    .build();
        }

    }

    @Test
    public void testEmbedding() {
        Document document = Document.builder().text("我是傻逼.").build();
        float[] embed = dashScopeEmbeddingModel.embed(document);
        System.out.println(embed.length);
        System.out.println(Arrays.toString(embed));
    }

    @BeforeEach
    public void init() {
        // 1. 声明内容⽂档
        Document doc = Document.builder()
                .text("2025年夏季奥运会将于巴黎举⾏, 预计吸引全球数百万观众")
                .build();
        Document doc2 = Document.builder()
                .text("对⽐学习框架下多语⾔BERT模型的语义表⽰分析")
                .build();
        Document doc3 = Document.builder()
                .text("暮⾊中的⽼槐树在⻛中摇曳, 枯枝划破绯红的晚霞")
                .build();
        Document doc4 = Document.builder()
                .text("基于Transformer的预训练模型在机器翻译中的迁移学习研究")
                .build();
        Document doc5 = Document.builder()
                .text(" 用户姓名是张三，职业是 Java 开发工程师。")
                .build();

        vectorStore.add(List.of(doc, doc2, doc3, doc4, doc5));
        System.out.println("向量初始化完成.");
    }

    @Test
    public void testRetrive() {
        List<Document> resultDoc = vectorStore.similaritySearch(SearchRequest.builder()
                .query("机器学习")
                .topK(3)
                .similarityThreshold(0.3f)
                .build());
        for (Document doc : resultDoc) {
            System.out.println(doc.toString());
        }
    }

    @Test
    public void testAdvisor() {

        // 1. 检索器：最多返回 3 条相关文档
        VectorStoreDocumentRetriever retriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .topK(3)
                .build();

        // 2. 将检索结果与用户问题拼接成提示词
        ContextualQueryAugmenter augmenter = ContextualQueryAugmenter.builder()
                .promptTemplate(new PromptTemplate("""
                    你是一个企业智能助手，请根据参考资料回答问题。
                    如果资料不足以回答，请回复“抱歉，我无法找到相关信息。”

                    【参考资料】
                    {context}

                    【问题】
                    {query}

                    【回答】
                    """))
                .emptyContextPromptTemplate(new PromptTemplate(
                        "请直接回复：抱歉，我无法找到相关信息。"))
                .build();

        // 3. 组装 Advisor
        RetrievalAugmentationAdvisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
                .documentRetriever(retriever)
                .queryAugmenter(augmenter)
                .build();

        ChatClient chatClient = ChatClient.builder(dashScopeChatModel).build();
        String content = chatClient.prompt()
                .user("奥运会什么时候举行?")
                .advisors(retrievalAugmentationAdvisor)
                .call()
                        .content();
        System.out.println(content);
    }


    @Test
    public void testAdvisor2() {
        QuestionAnswerAdvisor questionAnswerAdvisor = QuestionAnswerAdvisor.builder(vectorStore)
                .promptTemplate(new PromptTemplate("""
                        {query}
                        下⾯是上下⽂信息:
                        ---------------------
                        {question_answer_context}
                        ---------------------
                        根据给定的上下⽂信息, 回答问题, 并遵循以下规则:
                        1. 如果答案不在上下⽂中, 你就说不知道
                        2. 回复中避免使⽤"根据您提供的信息..."或者"根据上下⽂..."之类的语句
                        """))
                .searchRequest(SearchRequest.builder()
                        .topK(5)
                        .similarityThreshold(0.3f)
                        .build())
                .build();

        ChatClient chatClient = ChatClient.builder(dashScopeChatModel).build();
        String content = chatClient.prompt()
                .user("我是谁?")
                .advisors(questionAnswerAdvisor)
                .call()
                .content();
        System.out.println(content);
    }
}
