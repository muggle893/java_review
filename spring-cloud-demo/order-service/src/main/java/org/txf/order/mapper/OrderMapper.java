package org.txf.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.txf.order.model.Order;

@Mapper
public interface OrderMapper {

    @Select("select * from order_detail where id = #{id};")
    Order selectOrderById(@Param("id")Integer id);
}
