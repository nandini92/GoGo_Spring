package com.gogo_ecommerce.gogo.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public AllItemsResponse getItems(){
        return new AllItemsResponse(HttpStatus.OK, itemService.allItems(), "Successfully retrieved all items!");
    }

    @GetMapping("/item/{id}")
    public ItemResponse getItem(@PathVariable int id){
        Optional<Item> response = itemService.singleItem(id);

        if(response.isPresent()){ //.isPresent() to check for Optional.empty
            return new ItemResponse(HttpStatus.OK, response, "Successfully retrieved item!");
        } else {
            return new ItemResponse(HttpStatus.NOT_FOUND, response, "Could not find specific item, check id!");
        }        
    }

    record AllItemsResponse(
        HttpStatusCode status,
        List<Item> data,
        String message
    ){}

    record ItemResponse(
        HttpStatusCode status,
        Optional<Item> data,
        String message
    ){}
}
