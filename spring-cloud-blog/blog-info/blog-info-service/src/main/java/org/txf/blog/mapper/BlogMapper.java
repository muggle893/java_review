package org.txf.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.txf.blog.common.pojo.dataobject.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<BlogInfo> {
}
