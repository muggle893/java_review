package org.txf.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.txf.api.Product;

@Mapper
public  interface ProductMapper {
    @Select("select * from product_detail where id = #{id};")
    Product selectProductById(Integer id);
}
