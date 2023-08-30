package com.gogo_ecommerce.gogo.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<AllItemsResponse> getItems(){
        AllItemsResponse response = new AllItemsResponse(200, itemService.allItems(), "Successfully retrieved all items!");
        return new ResponseEntity<AllItemsResponse>(response, null, 200);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable int id){
        Optional<Item> item = itemService.singleItem(id);

        if(item.isPresent()){ //.isPresent() to check for Optional.empty
            ItemResponse response = new ItemResponse(200, item, "Successfully retrieved item!");

            return new ResponseEntity<ItemResponse>(response, null, 200);
        } else {
            ItemResponse response = new ItemResponse(404, item, "Could not find specific item, check id!");

            return new ResponseEntity<ItemResponse>(response, null, 404);
        }        
    }

    record AllItemsResponse(
        int status,
        List<Item> data,
        String message
    ){}

    record ItemResponse(
        int status,
        Optional<Item> data,
        String message
    ){}
}
