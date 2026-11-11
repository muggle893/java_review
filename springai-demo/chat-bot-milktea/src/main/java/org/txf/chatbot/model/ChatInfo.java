package org.txf.chatbot.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatInfo {
    private String chatId;
    private String title;

    public ChatInfo(String chatId, String chatName) {
        if (chatName == null) {
            this.title = "defaultChatName";
        } else {
            if (chatName.length() > 15) {
                this.title = chatName.substring(0, 15);
            } else {
                this.title = chatName;
            }
        }
        this.chatId = chatId;
    }
}
