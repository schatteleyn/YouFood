package controllers;

import java.util.List;
import models.Item;
import models.OrderClient;
import models.Restaurant;
import models.TableRest;
import play.data.validation.Required;
import play.mvc.Controller;

public class Orders extends Controller {
    
    public static void index() {
        //Clients.items(restaurant_id, category_id);
    }
    
    public static void find(Long id){
        OrderClient order = OrderClient.findById(id);
        render(order);
    }
        
    public static void saveCreate(Long table_id, List<Item> listItemsCard) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        
        TableRest table = TableRest.findById(table_id);
        Restaurant restaurant = table.restaurant;
        
        OrderClient order = new OrderClient(restaurant, table, listItemsCard);
        order.save();
        confirmation(restaurant.id, table.id);
    }
    
    public static void edit(Long id) {
        OrderClient order = OrderClient.findById(id);
        render(order);
    }
    
    public static void saveEdit(@Required Long id, @Required List<Item> items, @Required Float price, @Required TableRest table) {
        OrderClient order = OrderClient.findById(id);
        order.listItems = items;
        order.totalPrice = price;
        order.table = table;
        validation.valid(order);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          flash.success("The order has been updated !");
          order.save();
        }
        index();
    }
    
    public static void confirmation(Long restaurant_id, Long table_id) {
        Clients.index(restaurant_id, table_id);
        //destroy
    }
    
    public static void destroy(Long id) {
        OrderClient order = OrderClient.findById(id);
        order.delete();
        index();
    }
    
}
