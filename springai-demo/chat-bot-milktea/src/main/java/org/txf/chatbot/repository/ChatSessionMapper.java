package org.txf.chatbot.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.txf.chatbot.model.ChatSession;

@Mapper
public interface ChatInfoMapper {
    @Select("selectid, chat_id, title, created_time, updated_time from chat_session" +
            " where chat_id = #{chatId}")
    ChatSession selectChatSessionByChatId(@Param("chatId") String chatId);

    @Insert("insert into chat_session(chat_id, title) values(#{chatId}, #{title})")
    int insertChatSession(ChatSession chatSession);
}
