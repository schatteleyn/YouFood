package controllers;

import java.util.List;
import models.OrderClient;
import models.Restaurant;
import models.Waiter;
import play.data.validation.Required;
import play.mvc.Controller;

public class Waiters extends Controller {

    public static void index(Long restaurant_id) {
        Restaurants.show(restaurant_id);
    }
    
        
    public static void showOrders(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<OrderClient> orders = OrderClient.find("byRestaurant_id", restaurant_id).fetch();
        render(restaurant, orders);
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
        Waiter waiter = new Waiter(firstName, lastName, restaurant);
        restaurant.listWaiters.add(waiter);
        waiter.save();
        restaurant.save();
        
        index(restaurant.id);
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
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        
        for(int i=0; i<restaurant.listWaiters.size(); i++){
            if(i == waiter.id-1){
                restaurant.listWaiters.remove(i);
                restaurant.save();
                waiter.delete();
            }
        }
        index(restaurant_id);
    }
}   