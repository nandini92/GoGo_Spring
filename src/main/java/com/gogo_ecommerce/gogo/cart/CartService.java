package com.gogo_ecommerce.gogo.cart;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogo_ecommerce.gogo.order.Order;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(){
        UUID uuid = UUID.randomUUID();
        ArrayList<Order> emptyProducts = new ArrayList<Order>();
        Cart newCart = new Cart(uuid.toString(), emptyProducts);

        return cartRepository.insert(newCart);
    }

    public Optional<Cart> getCart(String id){
        return cartRepository.findById(id);
    }
}
