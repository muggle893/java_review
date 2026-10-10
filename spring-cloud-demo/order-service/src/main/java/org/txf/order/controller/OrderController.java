package org.txf.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.order.model.Order;
import org.txf.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/select")
    public Order getOrderById(Integer orderId) {
        Assert.notNull(orderId, "orderId must not be null");
        return orderService.getOrderById(orderId);
    }
}
