package controllers;

import java.util.List;
import models.Category;
import models.Item;
import models.Menu;
import models.Restaurant;
import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.mvc.Controller;

public class Menus extends Controller {
    
    public static void index() {
        List<Menu> menus = Menu.findAll();
        render(menus);
    }
    
    public static void show(Long id){
        Menu menu = Menu.findById(id);
        render(menu);
    }
    
    public static void saveCreate(@Required String name, List<Item> items) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        Menu menu = new Menu(name, items);
        
        List<Menu> menus = Menu.findAll();
        if(menus.isEmpty()){
            
            menu.currentMenu = true;
            menu.save();
            List<Restaurant> restaurants = Restaurant.findAll();
            
            for(int i=0; i<restaurants.size(); i++){
            restaurants.get(i).currentMenu = menu; 
                validation.valid(restaurants.get(i));
                if(validation.hasErrors()) {
                    // Message errors to test in views
                } else {
                    restaurants.get(i).save();
                }
            }
        }else{
            menu.save();
        }
        index();
    }
    
    public static void edit(Long id){
        Menu menu = Menu.findById(id);
        render(menu);
    }

    public static void saveEdit(@Required Long id, @Required String name) {
        Menu menu = Menu.findById(id);
        menu.name = name;
        validation.valid(menu);
        if(validation.hasErrors()) {
            // Message errors to test in views
        } else {
            flash.success("The menu has been updated !");
            menu.save();
        }
        show(id);
    }

    public static void editListItem(Long id){
        Menu menu = Menu.findById(id);
        List<Category> categories = Category.findAll();
        render(menu, categories);
    }

    public static void saveEditListItem(@Required Long id) {
        Menu menu = Menu.findById(id);

        String[] itemschk = params.getAll("item");
        
        for(int i = 0; i<menu.listItems.size(); i++){
            menu.listItems.remove(i);
        }
        
        for(int i = 0; i<itemschk.length; i++)
        {
            System.out.println(Long.parseLong(itemschk[i]));
            Item item = Item.findById(Long.parseLong(itemschk[i]));
            menu.listItems.add(item); 
        }

        
        validation.valid(menu);
        if(validation.hasErrors()) {
            // Message errors to test in views
            index();
        } else {
            menu.save();
            show(id);
        }
    }
    
    public static void destroy(Long id) {
        List<Restaurant> restaurants = Restaurant.find("byCurrentMenu_id", id).fetch();

        for(int i=0; i<restaurants.size(); i++){
            restaurants.get(i).currentMenu = null;
            validation.valid(restaurants.get(i));
            if(validation.hasErrors()) {
                // Message errors to test in views
            } else {
                restaurants.get(i).save();
            }
        }

        Menu menu = Menu.findById(id);
        menu.delete();
        index();
    }
}
