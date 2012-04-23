package controllers;

import models.Item;
import play.mvc.Controller;

public class ItemController extends Controller {

    public static void index() {
        Item.findAll();
    }
    
    public static void create(Item item) {
        item.save();
        index();
    }
    
    public static void edit(Long id) {
        Item item = Item.findById(id);
        item.edit("item", params.all());
        validation.valid(item);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          item.save();
        }
        index();
    }
    
    public static void destroy(Long id) {
        Item item = Item.findById(id);
        item.delete();
        index();
    }
}
