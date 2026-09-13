package org.txf.book_management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.txf.book_management.model.User;

@Mapper
public interface UserMapper {

    User getUserByUsername(@Param("username")String username);
}
