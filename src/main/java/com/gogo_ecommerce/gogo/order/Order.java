package com.gogo_ecommerce.gogo.order;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String _id;
    private String address;
    private String apt;
    private String city;
    private String creditCard;
    private String exp;
    private String fname;
    private String lname;
    private String phone;
    private String postalcode;
    private String province;
    private String svc;
    private ArrayList<com.gogo_ecommerce.gogo.cart.Order> cart;
}
