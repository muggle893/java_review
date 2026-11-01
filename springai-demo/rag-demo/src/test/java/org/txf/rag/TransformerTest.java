package org.txf.rag;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootTest
public class TransformerTest {

    @Test
    public void testTokenTextSplitter(@Value("classpath:/file/rule.txt") Resource resourcer) {
        // 1.先使用txtDocumentReader提取文本文件
        TextReader reader = new TextReader(resourcer);
        List<Document> documents = reader.get();

        // 2.使用TokenTextSplitter来拆分
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<Document> res = tokenTextSplitter.apply(documents);
        res.stream().forEach(System.out::println);


        // 构造函数参数介绍
        //public TokenTextSplitter(int chunkSize,
        // int minChunkSizeChars, int minChunkLengthToEmbed,
        // int maxNumChunks, boolean keepSeparator) {
        //            this.registry = Encodings.newLazyEncodingRegistry();
        //            this.encoding = this.registry.getEncoding(EncodingType.CL100K_BASE);
        //            this.chunkSize = chunkSize;
        //            this.minChunkSizeChars = minChunkSizeChars;
        //            this.minChunkLengthToEmbed = minChunkLengthToEmbed;
        //            this.maxNumChunks = maxNumChunks;
        //            this.keepSeparator = keepSeparator;

        // chunkSize 每块⽬标 token 数 (尽量不超过)
        // minChunkSizeChars 每块最少字符数 (防过短)
        // maxNumChunks 一个Document最多被切成多少快
        // minChunkLengthToEmbed 块最小长度，小于该长度的块会被丢弃
        // keepSeparator 是否保留分隔符号


    }

    @Autowired
    ChatModel chatModel;

    @Test
    public void KeywordMetadataEnricher(@Value("classpath:/file/rule.txt") Resource resourcer) {
        // 1.先使用txtDocumentReader提取文本文件
        TextReader reader = new TextReader(resourcer);
        List<Document> documents = reader.get();

        // 2.使用TokenTextSplitter来拆分
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<Document> res = tokenTextSplitter.apply(documents);
//        res.stream().forEach(System.out::println);

        // 3.使用KeywordMetadataEnricher, 进行关键词提取到metadata部分中
        // 关键词提取是依赖大模型
        KeywordMetadataEnricher keywordMetadataEnricher = KeywordMetadataEnricher.builder(chatModel)
                .keywordCount(2)
                .keywordsTemplate(
                        new PromptTemplate("""
                            根据给定的⽂本: {context_str}, ⽣成关键字, 只允许以下关键字
                            [会员宗旨, 会员类型, 会员注册, 积分制度, 会员权益, 会员⾏为, 会员服务, 公告, 隐
                            私保护]
                            只返回关键字, 其他信息不返回
                            """)
                )
                .build();
        List<Document> enricherRes = keywordMetadataEnricher.apply(res);
        enricherRes.stream().forEach(System.out::println);
    }


    // SummaryMetadataEnricher摘要生成器
    @Test
    public void SummaryMetadataEnricher(@Value("classpath:/file/rule.txt") Resource resourcer) {
        // 1.先使用txtDocumentReader提取文本文件
        TextReader reader = new TextReader(resourcer);
        List<Document> documents = reader.get();

        // 2.使用TokenTextSplitter来拆分
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<Document> res = tokenTextSplitter.apply(documents);
//        res.stream().forEach(System.out::println);

        // 3.生成摘要
        String template = "根据给定的文本：\n{context_str}\n\n, 返回30字以内的摘要.";
        SummaryMetadataEnricher summaryMetadataEnricher = new SummaryMetadataEnricher(chatModel,
                List.of(SummaryMetadataEnricher.SummaryType.PREVIOUS,
                        SummaryMetadataEnricher.SummaryType.CURRENT,
                        SummaryMetadataEnricher.SummaryType.NEXT),
                template,
                MetadataMode.NONE);
        List<Document> apply = summaryMetadataEnricher.apply(res);
        apply.stream().forEach(System.out::println);


    }
}
