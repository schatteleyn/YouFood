package controllers;

import java.util.List;
import models.Item;
import models.Menu;
import play.data.validation.Required;
import play.mvc.Controller;

public class Items extends Controller {

    public static void index() {
        List<Item> items = Item.findAll();
        render(items);
    }
    
    public static void create() {
        render();
    }

    public static void saveCreate(@Required String name, @Required Float price, @Required String type) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        
        Enum enumType = Item.Type.valueOf(type);
        Item item = new Item(name, price, enumType);
        
        item.save();
        index();
    }
    
    public static void edit(Long id) {
        Item item = Item.findById(id);
        render(item);
    }

    public static void saveEdit(@Required Long id, @Required String name, @Required Float price) {
        Item item = Item.findById(id);
        item.name = name;
        item.price = price;
        //item.type = type;
        validation.valid(item);
        if(validation.hasErrors()) {
            flash.error("Please correct these errors !");
        } else {
            flash.success("The item has been updated !");
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