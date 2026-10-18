package org.txf.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.txf.order.mapper.OrderMapper;
import org.txf.order.model.Order;
import org.txf.order.model.Product;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    public Order getOrderById(Integer id) {
        Assert.notNull(id, "id must not be null");
        log.info("service正在查询订单信息...");
        Order order = orderMapper.selectOrderById(id);
        // 发送http请求到商品服务，请求商品信息
        Long productId = order.getProductId();

        // 使用LoadBalanced进行负载均衡
        String url = "http://product-service/product/" + productId;
        log.info("拼接后的url:" + url);
        Product product = restTemplate.getForObject(url, Product.class);
        order.setProduct(product);
        log.info("得到订单信息：" + order.toString());
        return order;
    }

}
