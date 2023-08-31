package com.gogo_ecommerce.gogo.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<NewCartResponse> newCart() {
        NewCartResponse cart = new NewCartResponse(200, cartService.createCart(), "Cart created.");

        return new ResponseEntity<NewCartResponse>(cart, null, 200);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable String id) {
        Optional<Cart> cart = cartService.getCart(id);

        if(cart.isPresent()){
            CartResponse response = new CartResponse(200, cart, "Cart is present");

            return new ResponseEntity<CartResponse>(response, null, 200);
        } else {
            CartResponse response = new CartResponse(404, cart, "Cart is not present");

            return new ResponseEntity<CartResponse>(response, null, 404);
        }
    }

    @PatchMapping("/cart/{id}")
    public ResponseEntity<CartResponse> addItemToCart(@PathVariable String id, @RequestBody CartUpdateRequest update) {
        Optional<Cart> cart = cartService.addItemToCart(id, update.product, update.quantity);

        if(cart.isPresent()){
            CartResponse response  = new CartResponse(200, cart, "[ SUCCESS ]: cart updated");

            return new ResponseEntity<CartResponse>(response, null, 200);
        } else {
            CartResponse response  = new CartResponse(500, cart, "[ ERROR ]: updating cart in MongoDB");

            return new ResponseEntity<CartResponse>(response, null, 500);
        }
    }

    @PatchMapping("/remove-product-cart/{id}")
    public ResponseEntity<NewCartResponse> deleteItemFromCart(@PathVariable String id, @RequestBody OrderDeleteRequest deleteRequest) {
        Cart result = cartService.deleteItemFromCart(id, deleteRequest.product);

        NewCartResponse response  = new NewCartResponse(200, result, "[ SUCCESS ]: Product removed from cart");

        return new ResponseEntity<NewCartResponse>(response, null, 200); 
        
    }

    @DeleteMapping("/cart/{id}")
    public void deleteCart(@PathVariable String id){
        cartService.deleteCart(id);
    }

    record NewCartResponse(
        int status,
        Cart data,
        String message
    ){};

    record CartResponse(
        int status,
        Optional<Cart> data,
        String message
    ){};

    record CartUpdateRequest(
        int product,
        int quantity
    ){};

    record OrderDeleteRequest(
        int product
    ){};
}
