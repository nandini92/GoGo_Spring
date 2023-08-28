package com.gogo_ecommerce.gogo.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<Cart> newCart() {
        return new ResponseEntity<Cart>(cartService.createCart(), HttpStatus.OK);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Optional<Cart>> getCart(@PathVariable String id) {
        return new ResponseEntity<Optional<Cart>>(cartService.getCart(id), HttpStatus.OK);
    }
}
