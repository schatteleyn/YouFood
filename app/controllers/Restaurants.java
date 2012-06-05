package controllers;

import java.util.ArrayList;
import java.util.List;
import models.*;
import play.data.validation.Required;
import play.mvc.Controller;

public class Restaurants extends Controller {
    
    public static void index(){
        List<Restaurant> restaurants = Restaurant.findAll();
        render(restaurants);
    }
    
    public static void show(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<Waiter> waiters = Waiter.find("byRestaurant_id", restaurant_id).fetch();
        List<TableRest> tables = TableRest.find("byRestaurant_id", restaurant_id).fetch();
        render(restaurant, waiters, tables);
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
        
        List<Menu> menus = Menu.find("byCurrentMenu",true).fetch();
        
        Restaurant restaurant;
        if(menus.isEmpty()){
            restaurant = new Restaurant(address,city,country,null); 
        }else{
            restaurant = new Restaurant(address,city,country,menus.get(0));
        }

        restaurant.save();
        
        Kitchen kitchen = new Kitchen(restaurant);
        kitchen.listItems = new ArrayList<ItemToCook>();

        restaurant.kitchen = kitchen;

        kitchen.save();
        restaurant.save();
        
        index();
    }
    
    public static void edit(Long restaurant_id){
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        render(restaurant);
    }
    
    public static void saveEdit(@Required Long restaurant_id, @Required String country, @Required String city, @Required String address) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        restaurant.address = address;
        restaurant.city = city;
        restaurant.country = country;
        validation.valid(restaurant);
        if(validation.hasErrors()) {
            // Message errors to test in views
        } else {
            flash.success("The restaurant has been updated !");
            restaurant.save();
        }
        show(restaurant_id);
    }
    
    public static void destroy(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);

        Kitchen kitchen = restaurant.kitchen;
        if(kitchen != null){
            kitchen.listItems.clear();
            kitchen.delete();
        }
        
        restaurant.delete();
        index();
    }
    
}
