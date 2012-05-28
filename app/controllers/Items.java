package controllers;

import java.util.List;
import models.Category;
import models.Item;
import models.Menu;
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
        List<Menu> listMenus = Menu.findAll();
        List<Category> listCategories = Category.findAll();
        
        for(int i=0; i<listMenus.size(); i++){
            for(int y=0; y<listMenus.get(i).listItems.size(); y++){
                if(listMenus.get(i).listItems.get(y) == item){
                    listMenus.get(i).listItems.remove(y);
                }
            }
            listMenus.get(i).save();
        }

        for(int i=0; i<listCategories.size(); i++){
            for(int y=0; y<listCategories.get(i).listItems.size(); y++){
                if(listCategories.get(i).listItems.get(y) == item){
                    listCategories.get(i).listItems.remove(y);
                }
            }
            listCategories.get(i).save();
        }
        
        item.delete();
        index(item.category.id);
    }
}
