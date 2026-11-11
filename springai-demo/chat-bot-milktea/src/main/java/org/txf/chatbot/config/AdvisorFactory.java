package org.txf.chatbot.config;

import com.alibaba.cloud.ai.advisor.RetrievalRerankAdvisor;
import com.alibaba.cloud.ai.dashscope.rerank.DashScopeRerankModel;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvisorFactory {

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private DashScopeRerankModel rerankModel;

    public QuestionAnswerAdvisor createQuestionAnswerAdvisor() {
        String template = """
                【⽤⼾问题信息】
                {query}
                【上下⽂信息】
                {question_answer_context}
                回答请遵循以下规则：
                1. 若上下⽂信息中包含与⽤⼾问题直接相关的内容，即使表述⽅式略有不同，也
                应通过语义理解判断其相关性，并据此进⾏回复。
                2. 在上下⽂中确实⽆法找到与问题相关的任何信息时，请告知⽤⼾联系联系专业的人工客服。
                3. 回复时直接给出答案或解决⽅案，避免使⽤“根据您提供的信息”、“根据上下
                ⽂”等冗余表达。
                确保系统具备基本的语义匹配能⼒，不能仅依赖字⾯完全匹配来判断信息相关性
                """;
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template(template)
                .build();
        return QuestionAnswerAdvisor.builder(vectorStore)
                .promptTemplate(promptTemplate)
                .build();
    }

    public RetrievalRerankAdvisor createRetrievalRerankAdvisor() {
        RetrievalRerankAdvisor retrievalRerankAdvisor = new RetrievalRerankAdvisor(vectorStore, rerankModel,
                SearchRequest.builder().topK(100).build());
        return retrievalRerankAdvisor;
    }
}
