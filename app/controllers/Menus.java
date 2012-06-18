package controllers;

import java.util.List;
import models.Category;
import models.Item;
import models.Menu;
import models.Restaurant;
import play.data.validation.Required;
import play.mvc.Controller;

public class Menus extends Controller {
    
    public static void index() {
        List<Menu> menus = Menu.findAll();
        render(menus);
    }
    
    public static void show(Long menu_id){
        Menu menu = Menu.findById(menu_id);
        List<Category> categories = Category.findAll();
        render(menu, categories);
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
    
    public static void edit(Long menu_id){
        Menu menu = Menu.findById(menu_id);
        render(menu);
    }

    public static void saveEdit(@Required Long menu_id, @Required String name) {
        Menu menu = Menu.findById(menu_id);
        menu.name = name;
        validation.valid(menu);
        if(validation.hasErrors()) {
            // Message errors to test in views
        } else {
            flash.success("The menu has been updated !");
            menu.save();
        }
        show(menu_id);
    }
    
    public static void saveEditMenu(Long menu_id) {
        List<Restaurant> restaurants = Restaurant.findAll();
        List<Menu> menus = Menu.findAll();
        
        for(int i=0; i<menus.size(); i++){
            menus.get(i).currentMenu = false; 
            validation.valid(menus.get(i));
            if(validation.hasErrors()) {
                // Message errors to test in views
            } else {
                menus.get(i).save();
            }
        }

        Menu menu = Menu.findById(menu_id);
        menu.currentMenu = true;
        validation.valid(menu);
        if(validation.hasErrors()) {
            // Message errors to test in views
        } else {
            menu.save();
        }
        
        for(int i=0; i<restaurants.size(); i++){
           restaurants.get(i).currentMenu = menu; 
            validation.valid(restaurants.get(i));
            if(validation.hasErrors()) {
                // Message errors to test in views
            } else {
                restaurants.get(i).save();
            }
        }
        index();
    }

    public static void editListItem(Long menu_id){
        Menu menu = Menu.findById(menu_id);
        List<Category> categories = Category.findAll();
        render(menu, categories);
    }

    public static void saveEditListItem(@Required Long menu_id) {
        Menu menu = Menu.findById(menu_id);

        String[] itemschk = params.getAll("item");
        
        menu.listItems.clear();
        
        for(int i = 0; i<itemschk.length; i++)
        {
            Item item = Item.findById(Long.parseLong(itemschk[i]));
            menu.listItems.add(item); 
        }
        validation.valid(menu);
        if(validation.hasErrors()) {
            // Message errors to test in views
            index();
        } else {
            menu.save();
            show(menu_id);
        }
    }
    
    public static void removeAllItems(Long menu_id){
        Menu menu = Menu.findById(menu_id);
        menu.listItems.clear();
        
        menu.save();
        show(menu_id);
    }
    
    public static void destroy(Long menu_id) {
        List<Restaurant> restaurants = Restaurant.find("byCurrentMenu_id", menu_id).fetch();
        List<Menu> menus = Menu.findAll();
        Menu menu = Menu.findById(menu_id);
        
        if(menu.currentMenu == true){
            if(menus.size() == 1){
                for(int i=0; i<restaurants.size(); i++){
                    restaurants.get(i).currentMenu = null;
                    restaurants.get(i).save();
                }
            }else{
                if(menu.id == menus.get(0).id){
                    for(int i=0; i<restaurants.size(); i++){
                        restaurants.get(i).currentMenu = menus.get(1);
                        menus.get(1).currentMenu = true;
                        restaurants.get(i).save();
                        menus.get(1).save();
                    }
                }else{
                    for(int i=0; i<restaurants.size(); i++){
                        restaurants.get(i).currentMenu = menus.get(0);
                        restaurants.get(i).save();
                        menus.get(0).currentMenu = true;
                        restaurants.get(i).save();
                        menus.get(0).save();
                    }
                }
            }
        }

        menu.delete();        
        index();
    }
}
