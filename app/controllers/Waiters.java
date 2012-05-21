package controllers;

import models.Restaurant;
import models.Waiter;
import play.data.validation.Required;
import play.mvc.Controller;

public class Waiters extends Controller {

    public static void index(Long restaurant_id) {
        //Trouver une autre solution pour rediriger directement vers restaurants.show
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        render(restaurant);
    }
    
    public static void find(Long id){
        Waiter waiter = Waiter.findById(id);
        render(waiter);
    }
    
    public static void create(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        render(restaurant);
    }
    
    public static void saveCreate(@Required Long id, @Required String firstName, @Required String lastName) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index(id);
        }
        Restaurant restaurant = Restaurant.findById(id);
        Waiter waiter = new Waiter(firstName, lastName, restaurant, null);
        waiter.save();
        index(id);
    }
    
    public static void edit(Long id) {
        Waiter waiter = Waiter.findById(id);
        render(waiter);
    }
    public static void saveEdit(@Required Long id, @Required String firstName, @Required String lastName) {
        Waiter waiter = Waiter.findById(id);
        waiter.firstName = firstName;
        waiter.lastName = lastName;
        validation.valid(waiter);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          flash.success("The waiter has been updated !");
          waiter.save();
        }
        index(waiter.restaurant.id);
    }
    
    public static void destroy(Long id) {
        Waiter waiter = Waiter.findById(id);
        Long restaurant_id = waiter.restaurant.id;
        waiter.delete();
        index(restaurant_id);
    }
}   