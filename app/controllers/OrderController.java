package controllers;

import models.Order;
import play.mvc.Controller;

public class OrderController extends Controller {
    
    public static void index() {
        Order.findAll();
    }
    
    public static void find(Long id){
        Order order = Order.findById(id);
        render(order);
    }
    
    public static void create(Order order) {
        order.save();
        index();
    }
    
    public static void edit(Long id) {
        Order order = Order.findById(id);
        order.edit("order", params.all());
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
