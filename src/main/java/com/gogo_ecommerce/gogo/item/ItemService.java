package com.gogo_ecommerce.gogo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> allItems(){
        return itemRepository.findAll();
    }

    public Optional<Item> singleItem(int id){
        return itemRepository.findById(id);
    }
}
