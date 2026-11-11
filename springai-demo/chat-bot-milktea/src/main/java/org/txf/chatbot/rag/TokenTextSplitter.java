//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.txf.chatbot.rag;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;
import com.knuddels.jtokkit.api.IntArrayList;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Configuration
public class TokenTextSplitter extends TextSplitter {
    private static final int DEFAULT_CHUNK_SIZE = 800;
    private static final int MIN_CHUNK_SIZE_CHARS = 350;
    private static final int MIN_CHUNK_LENGTH_TO_EMBED = 5;
    private static final int MAX_NUM_CHUNKS = 10000;
    private static final boolean KEEP_SEPARATOR = true;
    private final EncodingRegistry registry;
    private final Encoding encoding;
    private final int chunkSize;
    private final int minChunkSizeChars;
    private final int minChunkLengthToEmbed;
    private final int maxNumChunks;
    private final boolean keepSeparator;

    public TokenTextSplitter() {
        this(40, 350, 5, 10000, true);
    }

    public TokenTextSplitter(boolean keepSeparator) {
        this(800, 350, 5, 10000, keepSeparator);
    }

    public TokenTextSplitter(int chunkSize, int minChunkSizeChars, int minChunkLengthToEmbed, int maxNumChunks, boolean keepSeparator) {
        this.registry = Encodings.newLazyEncodingRegistry();
        this.encoding = this.registry.getEncoding(EncodingType.CL100K_BASE);
        this.chunkSize = chunkSize;
        this.minChunkSizeChars = minChunkSizeChars;
        this.minChunkLengthToEmbed = minChunkLengthToEmbed;
        this.maxNumChunks = maxNumChunks;
        this.keepSeparator = keepSeparator;
    }

    public static Builder builder() {
        return new Builder();
    }

    protected List<String> splitText(String text) {
        return this.doSplit(text, this.chunkSize);
    }

    private int getLastPunctuation(String chunkText) {
        int lastPunctuation = 0;
        char[] cs = {'.', '?', '!', '。', '？', '！'};
        for (int i = 0; i < cs.length; i++) {
            lastPunctuation = Math.max(lastPunctuation, chunkText.lastIndexOf(cs[i]));
        }
        return lastPunctuation;
    }

    protected List<String> doSplit(String text, int chunkSize) {
        if (text != null && !text.trim().isEmpty()) {
            List<Integer> tokens = this.getEncodedTokens(text);
            List<String> chunks = new ArrayList();
            int num_chunks = 0;

            while(!tokens.isEmpty() && num_chunks < this.maxNumChunks) {
                List<Integer> chunk = tokens.subList(0, Math.min(chunkSize, tokens.size()));
                String chunkText = this.decodeTokens(chunk);
                if (chunkText.trim().isEmpty()) {
                    tokens = tokens.subList(chunk.size(), tokens.size());
                } else {
                    int lastPunctuation = getLastPunctuation(chunkText);

                    if (lastPunctuation != -1 && lastPunctuation > this.minChunkSizeChars) {
                        chunkText = chunkText.substring(0, lastPunctuation + 1);
                    }

                    String chunkTextToAppend = this.keepSeparator ? chunkText.trim() : chunkText.replace(System.lineSeparator(), " ").trim();
                    if (chunkTextToAppend.length() > this.minChunkLengthToEmbed) {
                        chunks.add(chunkTextToAppend);
                    }

                    tokens = tokens.subList(this.getEncodedTokens(chunkText).size(), tokens.size());
                    ++num_chunks;
                }
            }

            if (!tokens.isEmpty()) {
                String remaining_text = this.decodeTokens(tokens).replace(System.lineSeparator(), " ").trim();
                if (remaining_text.length() > this.minChunkLengthToEmbed) {
                    chunks.add(remaining_text);
                }
            }

            return chunks;
        } else {
            return new ArrayList();
        }
    }

    private List<Integer> getEncodedTokens(String text) {
        Assert.notNull(text, "Text must not be null");
        return this.encoding.encode(text).boxed();
    }

    private String decodeTokens(List<Integer> tokens) {
        Assert.notNull(tokens, "Tokens must not be null");
        IntArrayList tokensIntArray = new IntArrayList(tokens.size());
        Objects.requireNonNull(tokensIntArray);
        tokens.forEach(tokensIntArray::add);
        return this.encoding.decode(tokensIntArray);
    }

    public static final class Builder {
        private int chunkSize = 800;
        private int minChunkSizeChars = 350;
        private int minChunkLengthToEmbed = 5;
        private int maxNumChunks = 10000;
        private boolean keepSeparator = true;

        private Builder() {
        }

        public Builder withChunkSize(int chunkSize) {
            this.chunkSize = chunkSize;
            return this;
        }

        public Builder withMinChunkSizeChars(int minChunkSizeChars) {
            this.minChunkSizeChars = minChunkSizeChars;
            return this;
        }

        public Builder withMinChunkLengthToEmbed(int minChunkLengthToEmbed) {
            this.minChunkLengthToEmbed = minChunkLengthToEmbed;
            return this;
        }

        public Builder withMaxNumChunks(int maxNumChunks) {
            this.maxNumChunks = maxNumChunks;
            return this;
        }

        public Builder withKeepSeparator(boolean keepSeparator) {
            this.keepSeparator = keepSeparator;
            return this;
        }

        public TokenTextSplitter build() {
            return new TokenTextSplitter(this.chunkSize, this.minChunkSizeChars, this.minChunkLengthToEmbed, this.maxNumChunks, this.keepSeparator);
        }
    }
}
