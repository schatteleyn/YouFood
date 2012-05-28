package controllers;

import java.util.List;
import models.Item;
import models.OrderClient;
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
    
    public static void save(Long restaurant_id, Long category_id) {
        index();
    }
        
    public static void create() {
        render();
    }
    
    public static void saveCreate(List<Item> items, TableRest table) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        //Order order = new Order(restaurant, table, listItems);
        //order.save();
        index();
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
    
    public static void destroy(Long id) {
        OrderClient order = OrderClient.findById(id);
        order.delete();
        index();
    }
    
}
