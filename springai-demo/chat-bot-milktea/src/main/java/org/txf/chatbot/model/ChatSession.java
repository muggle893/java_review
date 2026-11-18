package org.txf.chatbot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatSession {
    private Integer id;
    private String chatId;
    private String title;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
