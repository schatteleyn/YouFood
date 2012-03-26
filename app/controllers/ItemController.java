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
        //item.edit(null, null);
        index();
    }
    
    public static void destroy(Long id) {
        Item item = Item.findById(id);
        item.delete();
        index();
    }
}
