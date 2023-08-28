package com.gogo_ecommerce.gogo.cart;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gogo_ecommerce.gogo.order.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String _id;
    private ArrayList<Order> products;
}
