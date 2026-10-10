package org.txf.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.txf.product.mapper.ProductMapper;
import org.txf.product.model.Product;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getProductById(Integer id) {
        Assert.notNull(id, "id must not be null");
        return productMapper.selectProductById(id);
    }
}
