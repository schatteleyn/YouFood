package controllers;

import java.util.ArrayList;
import java.util.Date;
import models.*;
import play.mvc.Controller;

public class Orders extends Controller {
    
    public static void saveCreate(Long restaurant_id, Long card_id) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        
        Card card = Card.findById(card_id);
        TableRest table = card.table;
        Restaurant restaurant = card.restaurant;
        
        OrderClient order = new OrderClient(restaurant, table);
        order.date = new Date();
        order.totalPrice = card.totalPrice;
        order.listItems = new ArrayList<Item>();
        
        for(int i=0; i<card.listItems.size(); i++){
            order.listItems.add(card.listItems.get(i));
        }

        order.save();
        Cards.destroy(table.id);
    }
    
    public static void orderComplete(Long order_id) {
        OrderClient order = OrderClient.findById(order_id);
        order.inProgress = false;
        order.save();
        
        Waiters.showCurrentOrders(order.restaurant.id);
    }
    
    public static void destroy(Long order_id) {
        OrderClient order = OrderClient.findById(order_id);
        order.delete();
    }
    
}
