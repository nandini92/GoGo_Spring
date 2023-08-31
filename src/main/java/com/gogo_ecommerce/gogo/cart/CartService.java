package com.gogo_ecommerce.gogo.cart;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Cart> addItemToCart(String id, int product, int quantity) {
        Cart cart = cartRepository.findById(id).orElse(null);
        Boolean productExists = false;

        if(cart != null) {
            ArrayList<Order> orders = cart.getProducts();

            for(Order o : orders){
                if( o.getProduct() == product ){
                    o.setQuantity(quantity);
                    productExists = true;
                }
            }

            if(productExists == false){
                orders.add(new Order(product, quantity ));
            }
            cartRepository.save(cart);
        }

        return cartRepository.findById(id);
    }

    public Cart deleteItemFromCart(String id, int product){
        Cart cart = cartRepository.findById(id).orElse(null);

        if(cart != null) {
            ArrayList<Order> orders = cart.getProducts();
            Order order = new Order();

            for(Order o : orders){
                if( o.getProduct() == product ){
                    order = o;
                    break;
                }
            }
            
            orders.remove(order);
        }
        
        Cart results = cartRepository.save(cart);

        return results;
    }

    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }
}
