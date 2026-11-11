package org.txf.chatbot.rag;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 关键词提取
 */
@Component
public class KeyWordEnricher {
    @Autowired
    private DashScopeChatModel chatModel;

    public List<Document> enrich(final List<Document> documents) {
        KeywordMetadataEnricher enricher = KeywordMetadataEnricher
                .builder(chatModel)
                .keywordCount(3)
                .build();
        return enricher.apply(documents);
    }
}
