package controllers;

import models.Category;
import models.Item;
import play.data.validation.Required;
import play.mvc.Controller;

public class Items extends Controller {

    public static void index(Long category_id) {
        Categories.show(category_id);
    }
    
    public static void create(Long id) {
        Category category = Category.findById(id);
        render(category);
    }

    public static void saveCreate(@Required Long id, @Required String name, @Required Float price) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        
        Category category = Category.findById(id);
        
        Item item = new Item(name, price, category);
        category.listItems.add(item);

        item.save();
        category.save();
        index(id);
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
        index(item.category.id);
    }
    
    public static void destroy(Long id) {
        Item item = Item.findById(id);
        item.delete();
        index(item.category.id);
    }
}
