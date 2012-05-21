package controllers;

import java.util.List;
import models.Category;
import models.Item;
import play.data.validation.Required;
import play.mvc.Controller;

public class Items extends Controller {

    public static void index() {
        List<Item> items = Item.findAll();
        List<Category> categories = Category.findAll();
        render(items, categories);
    }
    
    public static void create() {
        List<Category> categories = Category.findAll();
        render(categories);
    }

    public static void saveCreate(@Required String name, @Required Float price, @Required Long category_id) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        
        Category category = Category.findById(category_id);
        Item item = new Item(name, price, category);
        
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
