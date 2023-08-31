package com.gogo_ecommerce.gogo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order addOrder(Order order){
        return orderRepository.insert(order);
    }
}
