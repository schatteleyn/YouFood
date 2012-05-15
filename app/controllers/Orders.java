package controllers;

import java.util.Date;
import java.util.List;
import models.Item;
import models.Order;
import models.Table;
import play.data.validation.Required;
import play.mvc.Controller;

public class Orders extends Controller {
    
    public static void index() {
        Order.findAll();
    }
    
    public static void find(Long id){
        Order order = Order.findById(id);
        render(order);
    }
    
    public static void create(List<Item> items, Date date, Float price, Table table) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        Order order = new Order(items, date, price, table);
        order.save();
        index();
    }
    
    public static void edit(Long id) {
        Order order = Order.findById(id);
        render(order);
    }
    
    public static void saveEdit(@Required Long id, @Required List<Item> items, @Required Float price, @Required Table table) {
        Order order = Order.findById(id);
        order.listItems = items;
        order.totalPrice = price;
        order.table = table;
        validation.valid(order);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          order.save();
        }
        index();
    }
    
    public static void destroy(Long id) {
        Order order = Order.findById(id);
        order.delete();
        index();
    }
    
}
