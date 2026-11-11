package org.txf.chatbot.repository;

import org.txf.chatbot.model.ChatInfo;

import java.util.List;

public interface ChatInfoRepository {
    /**
     * 保存会话记录
     * @return
     */
    void save(String chatId, String chatName);


    /**
     * 获取会话列表
     * @return
     */
    List<ChatInfo> getChatInfoList();

    void deleteChatInfo(String chatId);
}
