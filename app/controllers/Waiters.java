package controllers;

import java.util.ArrayList;
import java.util.List;
import models.OrderClient;
import models.Restaurant;
import models.TableRest;
import models.Waiter;
import play.data.validation.Required;
import play.mvc.Controller;

public class Waiters extends Controller {

    public static void index(Long restaurant_id) {
        Restaurants.show(restaurant_id);
    }

    public static void showCurrentOrders(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<OrderClient> listOrders = OrderClient.find("byRestaurant_id", restaurant_id).fetch();
        List<OrderClient> listCurrentOrders = new ArrayList<OrderClient>();
        
        for(int i=0; i<listOrders.size(); i++){
            if(listOrders.get(i).inProgress == true){
                listCurrentOrders.add(listOrders.get(i));
            }
        }
        
        render(restaurant, listCurrentOrders);
    }
    
    public static void showPreviousOrders(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<OrderClient> listOrders = OrderClient.find("byRestaurant_id", restaurant_id).fetch();
        List<OrderClient> listOldOrders = new ArrayList<OrderClient>();
        
        for(int i=0; i<listOrders.size(); i++){
            if(listOrders.get(i).inProgress == false){
                listOldOrders.add(listOrders.get(i));
            }
        }
        
        render(restaurant, listOldOrders);
    }
    
    public static void showCurrentOrderDetailed(Long restaurant_id, Long order_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        OrderClient order = OrderClient.findById(order_id);

        render(restaurant, order);
    }
    
    public static void showStatusTables(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<TableRest> listTables = TableRest.find("byRestaurant_id", restaurant_id).fetch();
        
        render(restaurant, listTables);
    }
    
    public static void find(Long id){
        Waiter waiter = Waiter.findById(id);
        
        render(waiter);
    }
    
    public static void saveCreate(@Required Long restaurant_id, @Required String firstName, @Required String lastName) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index(restaurant_id);
        }
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Waiter waiter = new Waiter(firstName, lastName, restaurant);
        restaurant.listWaiters.add(waiter);
        waiter.save();
        restaurant.save();
        
        index(restaurant.id);
    }
    
    public static void edit(Long restaurant_id, Long waiter_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Waiter waiter = Waiter.findById(waiter_id);
        render(restaurant,waiter);
    }
    
    public static void saveEdit(@Required Long waiter_id, @Required String firstName, @Required String lastName) {
        Waiter waiter = Waiter.findById(waiter_id);
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
    
    public static void destroy(Long waiter_id) {
        Waiter waiter = Waiter.findById(waiter_id);
        Long restaurant_id = waiter.restaurant.id;
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        
        for(int i=0; i<restaurant.listWaiters.size(); i++){
            if(restaurant.listWaiters.get(i).id == waiter.id){
                restaurant.listWaiters.remove(i);
                restaurant.save();
                waiter.delete();
            }
        }
        
        index(restaurant_id);
    }
}   