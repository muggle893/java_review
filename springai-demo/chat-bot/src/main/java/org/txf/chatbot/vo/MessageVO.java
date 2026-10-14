package org.txf.chatbot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageVO {
    private String role;
    private String content;
    public MessageVO(Message message) {
        switch(message.getMessageType()) {
            case USER:
                role = "user";
                break;
            case ASSISTANT:
                role = "assistant";
                break;
            case SYSTEM:
                role = "system";
                break;
            case TOOL:
                role = "tool";
            break;
        }
        this.content = message.getText();
    }

}
