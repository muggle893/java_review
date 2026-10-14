package org.txf.chatbot.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.txf.chatbot.model.ChatInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("chatrepo")
@Slf4j
public class ChatInfoRepositoryImpl implements ChatInfoRepository {
    private Map<String, String> map = new LinkedHashMap<>();

    @Override
    public void save(String chatId, String chatName) {
        log.info("save chatId: {} chatName: {}", chatId, chatName);
        map.putIfAbsent(chatId, chatName);
    }

    @Override
    public List<ChatInfo> getChatInfoList() {
        return map.entrySet()
                .stream()
                .map(entry -> new ChatInfo(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteChatInfo(String chatId) {
        map.remove(chatId);
    }
}
