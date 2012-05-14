package controllers;

import java.util.List;
import models.Restaurant;
import play.data.validation.Required;
import play.mvc.Controller;

public class Restaurants extends Controller {
    
    public static void index(){
        List<Restaurant> restaurants = Restaurant.findAll();
        render(restaurants);
    }
    
    public static void show(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        render(restaurant);
    }
    
    public static void create(@Required String country, @Required String city, @Required String address){
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
    
    public static void destroy(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        restaurant.delete();
        index();
    }
    
}
