package org.txf.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.txf.user.common.pojo.dataobject.UserInfo;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
