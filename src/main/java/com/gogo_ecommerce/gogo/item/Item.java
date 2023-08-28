package com.gogo_ecommerce.gogo.item;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "items")
@Data // Takes care of getters, setters, toString, hashcode, equals
@AllArgsConstructor // Constructors with all args
@NoArgsConstructor // Constructors with no args
public class Item {
    private Integer _id;
    private String name;
    private String price;
    private String body_location;
    private String category;
    private String imageSrc;
    private int numInStock;
    private int companyId;
}
