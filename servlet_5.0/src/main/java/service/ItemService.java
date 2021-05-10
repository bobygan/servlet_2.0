package service;

import dao.ItemDAO;
import models.Item;
import org.hibernate.HibernateException;

import java.io.IOException;

public class ItemService {

    private ItemDAO<Item> dao = new ItemDAO<>(Item.class);


    public Item save(Item item) throws IllegalAccessException {
        try {
            dao.findById(item.getId());
            throw new IllegalAccessException("\nItem already registered with this ID");

        } catch (NullPointerException e) {
            return dao.save(item);
        }
    }

    public Item update(Item item) throws IllegalAccessException {
        try {
            dao.findById(item.getId());
            return dao.update(item);
        } catch (NullPointerException e) {
            throw new IllegalAccessException("\nItem not registered with this ID");
        }

    }


    public Item findById(Long id) throws IllegalAccessException {
        try {
            return dao.findById(id);
        } catch (NullPointerException e)  {
            throw new IllegalAccessException("\nItem not registered with this ID");
        }
    }


    public void delet(Long id) throws IllegalAccessException {
        try {
            dao.findById(id);
            dao.delete(id);
        } catch (NullPointerException e) {
            throw new IllegalAccessException("\nItem not registered with this ID");
        }


    }


}
