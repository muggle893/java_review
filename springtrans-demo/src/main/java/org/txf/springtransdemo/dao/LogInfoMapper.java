package org.txf.springtransdemo.dao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogInfoMapper {
    @Insert("insert into log_info(`user_name`,`op`)values(#{name},#{op})")
    Integer insertLog(String name,String op);
}
