package com.gogo_ecommerce.gogo.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Optional<Order> getOrder(String id){
        return orderRepository.findById(id);
    }

    public Order addOrder(Order order){
        return orderRepository.insert(order);
    }
}
