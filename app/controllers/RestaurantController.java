package controllers;

import play.mvc.Controller;
import models.Restaurant;

public class RestaurantController extends Controller {
    
    public static void show(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        render(restaurant);
    }
    
    public static void create(Restaurant restaurant){
        restaurant.save();
        Application.index();
    }
    
    public static void edit(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        restaurant.edit("restaurant", params.all());
        validation.valid(restaurant);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          restaurant.save();
        }
        Application.index();
    }
    
    public static void destroy(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        restaurant.delete();
        Application.index();
    }
    
}
