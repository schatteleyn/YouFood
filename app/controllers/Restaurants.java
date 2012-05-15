package controllers;

import java.util.List;
import models.Menu;
import models.Restaurant;
import play.data.validation.Required;
import play.mvc.Controller;

public class Restaurants extends Controller {
    
    public static void index(){
        List<Restaurant> restaurants = Restaurant.findAll();
        List<Menu> menus = Menu.findAll();
        render(restaurants,menus);
    }
    
    public static void show(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        render(restaurant);
    }
        
    public static void create(){
        render();
    }
    
    public static void saveCreate(@Required String country, @Required String city, @Required String address){
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        Restaurant restaurant = new Restaurant(address,city,country,null);
        restaurant.save();
        index();
    }
    
    public static void edit(Long id){
        Restaurant restaurant = Restaurant.findById(id);
        render(restaurant);
    }
    
    public static void saveEdit(@Required Long id, @Required String country, @Required String city, @Required String address) {
        Restaurant restaurant = Restaurant.findById(id);
        restaurant.address = address;
        restaurant.city = city;
        restaurant.country = country;
        validation.valid(restaurant);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          restaurant.save();
        }
        show(id);
    }
    
    public static void saveEditMenu(@Required Long menu_id) {
        List<Restaurant> restaurants = Restaurant.findAll();
        Menu menu = Menu.findById(menu_id);
        
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
    
    public static void destroy(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        restaurant.delete();
        index();
    }
    
}
