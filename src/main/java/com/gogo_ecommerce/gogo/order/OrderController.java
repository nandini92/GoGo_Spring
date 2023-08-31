package com.gogo_ecommerce.gogo.order;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gogo_ecommerce.gogo.cart.Cart;
import com.gogo_ecommerce.gogo.item.Item;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> newOrder(@RequestBody NewOrder order){
        System.out.println(order);

        if (order.creditCard.length() <= 13 || order.creditCard.length() > 19 ){
            OrderResponse response  = new OrderResponse(400, "Invalid credit card.");
             return new ResponseEntity<OrderResponse> (response, null, 400);
            
        } else if (order.exp.length() != 4 ){
            OrderResponse response  = new OrderResponse(400, "Invalid Expiry Date. Please provide expiry Date in MMYY format.");
             return new ResponseEntity<OrderResponse> (response, null, 400);
            
        } else if (order.svc.length() != 3){
            OrderResponse response  = new OrderResponse(400, "Invalid SVC. ");
             return new ResponseEntity<OrderResponse> (response, null, 400);
            
        } else {
            String _id = UUID.randomUUID().toString();
            Order newOrder = new Order(_id, order.fname, order.lname, order.phone, order.address, order.apt, order.city, order.creditCard, order.exp, order.svc, order.postalcode, order.province, order.cart);
            Order orderCreated = orderService.addOrder(newOrder);

            if(orderCreated != null){
                OrderResponse response = new OrderResponse(200, "Order succesfully created!");

                return new ResponseEntity<OrderResponse> (response, null, 200);
            } else {
                OrderResponse response = new OrderResponse(500, "Error encountered while processing order!");

                return new ResponseEntity<OrderResponse> (response, null, 500);
            }
        }
    }

    record NewOrder(
    String address,
    String apt,
    String city,
    String creditCard,
    String exp,
    String fname,
    String lname,
    String phone,
    String postalcode,
    String province,
    String svc,
    ArrayList<com.gogo_ecommerce.gogo.cart.Order> cart
    ){};

    record OrderResponse(
        int status,
        String message
    ){};
}
