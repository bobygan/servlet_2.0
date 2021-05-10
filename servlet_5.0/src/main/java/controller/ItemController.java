package controller;


import models.Item;
import service.ItemService;

public class ItemController {
    private ItemService service = new ItemService();

    public Item save(Item item) throws IllegalAccessException {
       return service.save(item);
    }
    public Item update(Item item) throws IllegalAccessException {
        return service.update(item);
    }
    public Item findById(Long id) throws IllegalAccessException {
        return service.findById(id);
    }
    public void delet(Long id) throws IllegalAccessException {
         service.delet(id);
    }

}
